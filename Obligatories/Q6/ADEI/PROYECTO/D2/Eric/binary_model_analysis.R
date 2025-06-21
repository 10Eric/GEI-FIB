
# Cargar paquetes necesarios
library(tidyverse)
library(caret)
library(pROC)
library(splines)
library(DescTools)

# Cargar los datos
df <- read.csv("adult_def.csv")

# Convertir la variable objetivo a factor binario
df$income <- as.factor(df$income)

# Separar en entrenamiento (80%) y prueba (20%)
set.seed(123)
trainIndex <- createDataPartition(df$income, p = .8, list = FALSE)
train <- df[trainIndex, ]
test <- df[-trainIndex, ]

# 1. Construcción del modelo con covariables numéricas significativas
numeric_vars <- c("age", "fnlwgt", "edu_num", "cap_gain", "cap_loss", "hours_week")
model_numeric <- glm(income ~ ., data = train[, c(numeric_vars, "income")], family = binomial)

summary(model_numeric)

# 2. Exploración de relaciones no lineales
par(mfrow=c(2,3))
for (v in numeric_vars) {
  logit <- glm(as.numeric(income == '>50K') ~ get(v), data = train, family = binomial)
  plot(train[[v]], logit$fitted.values, main=v, xlab=v, ylab='Log-odds')
}

# Si alguna relación es no lineal, aplicamos transformaciones:
# Ejemplo con spline para cap_gain si se detecta no linealidad
model_spline <- glm(income ~ age + fnlwgt + edu_num + ns(cap_gain, df=3) + cap_loss + hours_week, 
                    data = train, family = binomial)

# 3. Incorporación de factores categóricos y comparación con modelo previo
model_factors <- update(model_spline, . ~ . + workclass + marital + occupation + relationship + race + sex + native_country)
anova(model_spline, model_factors, test="Chisq")

# Elegir niveles de referencia explícitamente si es necesario
train$sex <- relevel(train$sex, ref = "Male")

# 4. Iteraciones de modelado
# Mostrar resumen de mejoras con cada paso y comparación de deviance
summary(model_factors)

# 5. Diagnóstico del modelo final
# Predicciones en el conjunto de prueba
pred_probs <- predict(model_factors, newdata = test, type = "response")
pred_class <- ifelse(pred_probs > 0.5, ">50K", "<=50K")
pred_class <- factor(pred_class, levels=c("<=50K", ">50K"))

# Matriz de confusión
conf_matrix <- confusionMatrix(pred_class, test$income)
print(conf_matrix)

# Curva ROC y AUC
roc_obj <- roc(test$income, pred_probs, levels=c("<=50K", ">50K"))
plot(roc_obj)
auc(roc_obj)

# Influencia: distancia de Cook
cooks_d <- cooks.distance(model_factors)
influential <- which(cooks_d > (4 / nrow(train)))
print(influential)

# Interpretación en términos de odds ratio
exp(coef(model_factors))

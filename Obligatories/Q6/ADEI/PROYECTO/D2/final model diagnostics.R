# 1) Partició 80/20 (si no està feta prèviament)
set.seed(123)
total_n   <- nrow(dd)
train_idx <- sample(total_n, size = round(0.8 * total_n))
train     <- dd[train_idx, ]
test      <- dd[-train_idx, ]

# Transformar la resposta a factor binari
train$income_bin <- factor(ifelse(train$income == ">50K", "1", "0"), levels = c("0","1"))
test$income_bin  <- factor(ifelse(test$income  == ">50K", "1", "0"), levels = c("0","1"))

# 2) Ajustar el model sobre train
model_train <- glm(
  income_bin ~ age + edu_num + cap_gain + cap_loss + hours_week + sex + marital + occupation + hours_week:occupation,
  data   = train,
  family = binomial
)

# 3) Prediccions sobre el test set
probs <- predict(model_train, newdata = test, type = "response")
preds <- factor(ifelse(probs > 0.5, "1", "0"), levels = c("0","1"))

# 4) Matriu de confusió i mètriques  (Slides §4.3-5.1)
tab        <- table(Pred = preds, True = test$income_bin)
TP         <- tab["1","1"]
TN         <- tab["0","0"]
FP         <- tab["1","0"]
FN         <- tab["0","1"]
accuracy   <- (TP + TN) / sum(tab)
sensitivity<- TP / (TP + FN)    # Sn = a/(a+c)
specificity<- TN / (TN + FP)    # Sp = d/(b+d)
cat(sprintf("Accuracy    = %.3f\n", accuracy))
cat(sprintf("Sensitivity = %.3f\n", sensitivity))
cat(sprintf("Specificity = %.3f\n", specificity))

# 5) Corba ROC i AUC  (Slides §4.3-5.2)
library(pROC)
roc_obj <- roc(test$income_bin, probs)
plot(roc_obj, main = "ROC Curve – Test set")
cat(sprintf("AUC = %.3f\n", auc(roc_obj)))

# 6) Observacions influents (Cook's distance)  (Slides §4.3-6.2)
cooks_d   <- cooks.distance(model_train)
p <- length(coef(model_train))
threshold <- 4 / (nrow(train) - p)
influents <- which(cooks_d > threshold)
cat(sprintf("Cook's D threshold = %.4f\n", threshold))
cat("Influential obs indices:", influents, "\n")

# Opcional: visualitzar
plot(cooks_d, type = "h", main = "Cook's distance", ylab = "Cook's D")
abline(h = threshold, col = "red", lty = 2)

# 7) Odds ratios i IC 95% (inferencia de GLM)
or      <- exp(coef(model_train))
ci      <- exp(confint(model_train))
or_tab  <- data.frame(
  Predictor = names(or),
  OR        = or,
  CI_low    = ci[,1],
  CI_high   = ci[,2]
)
print(or_tab)
library(MASS) #used for Boxcox
library(car) #used for BoxTidwell among others

setwd("~/Escritorio/ADEI/D2")
dd <- read.csv("~/Escritorio/ADEI/D2/adult_def.csv")
initial_model <- lm(income_integer ~ age + edu_num + cap_gain + cap_loss + hours_week, data = dd)
summary(initial_model)
par(mfrow=c(2,2))
plot(initial_model)

#target variable transformation, so the normality assumption is met
boxcox(income_integer ~ age + edu_num + cap_gain + cap_loss + hours_week, data = dd)

#given lambda is aproximatedly -1 we do the inverse transformation
y_trans <- 1 / dd$income_integer
transformed_model <- lm(y_trans ~ age + edu_num + cap_gain + cap_loss + hours_week, data = dd)
summary(transformed_model)
plot(transformed_model) #we cannot accept the basic hypothesis yet
boxcox(y_trans ~ age + edu_num + cap_gain + cap_loss + hours_week, data = dd) # lambda is now around 1 meaning no transformation is needed for the response variable

#As seen before, the basic hypothesis cannot be accepted, we need to perform transformation on the regressors
boxTidwell(income_integer ~ age + edu_num + hours_week, data = dd)
agebt <- 1 / sqrt(dd$age)
edu_num_bt <- sqrt(dd$edu_num)
btmodel <- lm(income_integer ~ agebt + edu_num_bt + cap_gain + cap_loss + hours_week, data = dd)
summary(btmodel)
plot(btmodel)

#Check cook's distance
plot(cooks.distance(btmodel))

#Try adding polynomial terms
age2 <- dd$age^2
hours_week2 <- dd$hours_week^2

model_poly <- lm(income_integer ~ age + age2 + edu_num + cap_gain + cap_loss + hours_week + hours_week2 , data = dd)

#comparing model performance
summary(model_poly)
anova(initial_model, model_poly)
plot(model_poly)

#Incorporating Factors
#Add Occupation
modelo_occ <- update(btmodel, . ~ . + occupation)
anova(btmodel, modelo_occ)  # p < 2.2e-16 ***

# Add estado civil (7 categories)
modelo_marital <- update(modelo_occ, . ~ . + marital)
anova(modelo_occ, modelo_marital)  # p = < 2.2e-16 

# Add género (2 categories)
modelo_gender <- update(modelo_marital, . ~ . + sex)
anova(modelo_marital, modelo_gender)  # p = 0.008045 ***

# Add clase trabajadora (9 categories)
modelo_workclass <- update(modelo_gender, . ~ . + workclass)
anova(modelo_gender, modelo_workclass)  # p = < 2.2e-16

# Add relación familiar (6 categories)
modelo_relat <- update(modelo_workclass, . ~ . + relationship)
anova(modelo_workclass, modelo_relat)  # p = < 2.2e-16

# Add raza (5 categories)
modelo_race <- update(modelo_relat, . ~ . + race) 
anova(modelo_relat, modelo_race)  # p = 1.172e-08

# Add país origen (42 categorías)
modelo_country <- update(modelo_race, . ~ . + native_country)
anova(modelo_race, modelo_country)  # p = 5.832e-09

#Add income (2 categorias)
modelo_income <- update(modelo_country,. ~ . + income)
anova(modelo_country,modelo_income)

#Model with all significant variables including categorical variables
catmodel <- lm(income_integer ~ agebt + edu_num_bt + cap_gain + cap_loss + hours_week + occupation + marital + sex + workclass + relationship + race + native_country + income, data = dd)
stepmodel <- stepAIC(catmodel, direction = "back")

vif(catmodel)
summary(catmodel)
anova(catmodel)
anova(btmodel, catmodel)
plot(catmodel)



#Possible Conclusio
avPlots(btmodel,id=list(method=hatvalues(catmodel),n=5))
library(effects)
plot(allEffects(catmodel))

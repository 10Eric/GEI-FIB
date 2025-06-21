library(MASS) #used for Boxcox
library(car) #used for BoxTidwell among others

setwd("/home/adpaoj/Documents/ADEI/Project/B2")
dd <- read.csv("~/Documents/ADEI/Project/B2/adult_def.csv")
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
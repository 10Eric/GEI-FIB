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
boxTidwell(y_trans ~ age + edu_num + hours_week, data = dd)
dd$agebt <- sqrt(dd$age)
edu_num_bt <- sqrt(dd$edu_num)
btmodel <- lm(y_trans ~ agebt + edu_num_bt + cap_gain + cap_loss + hours_week, data = dd)
summary(btmodel)
par(mfrow=c(2,2))
plot(btmodel)


#Check cook's distance
plot(cooks.distance(btmodel))

#Try adding polynomial terms

age2 <- dd$agebt^2
hours_week2 <- dd$hours_week^2

model_poly <- lm(y_trans ~ agebt + age2 + edu_num + cap_gain + cap_loss + hours_week + hours_week2 , data = dd)

#comparing model performance
summary(model_poly)
anova(initial_model, model_poly)
par(mfrow=c(2,2))
plot(model_poly)


#model with interactions
inter_model <- lm(y_trans ~ age + workclass + fnlwgt + edu_num + marital +
              occupation + relationship + race + sex + cap_gain + cap_loss +
              hours_week + native_country +
              sex*marital + hours_week*occupation, data = dd)
summary(inter_model)

#BINARY

#First we transform our income variable into a binary response variable we can use for our logistic-regression model
income_bin <- ifelse(dd$income == ">50K", 1, 0)
#transforming variable into factor
income_bin <- as.factor(income_bin)

#Now we divide the sample into 80% training and 20% test 
# Set a seed so results are reproducible
set.seed(123)  

# Number of observations
n <- nrow(dd)

# Create a random sample of row numbers for the training set (80% of the data)
train_indices <- sample(1:n, size = 0.8 * n)

# Create training and test sets
train_data <- dd[train_indices, ]
test_data <- dd[-train_indices, ]

initial_model_b <- glm(income_bin ~ age + edu_num + cap_gain + cap_loss + hours_week, data = train_data, family = binomial)
summary(initial_model_b)
plot(initial_model_b) #the basic hypothesis are met, beware of Homoscedasticity
#Check cook's distance
plot(cooks.distance(initial_model_b)) #there are some influential observations that skew the data a little



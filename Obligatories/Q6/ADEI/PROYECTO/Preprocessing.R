install.packages("dplyr")
library(dplyr)

setwd("/home/adpaoj/Documents/ADEI/Project")
dd <- read.csv("~/Documents/ADEI/Project/adult.csv")
table(dd$native.country)

#Numerical (prior to preprocessing)

class(dd[,1])
for ( i in 1:15){
  if(is.numeric(dd[,i])){
    hist(dd[,i],main=paste("hist of", names(dd)[i]))
    boxplot(dd[,i],main=paste("boxplot of", names(dd)[i]))
    
    table(dd[,i])
    summary(dd[,i])
  } else{
    table(dd[,i])
    barplot(table(dd[,i]),main=paste("barplot of", names(dd)[i]))
    pie(table(dd[,i]),main=paste("pie of", names(dd)[i]))
  }
}


#PREPROCESSING

dd <- read.csv("~/Documents/ADEI/Project/adult.csv")  # Read data

#Rename columns
colnames(dd) <- c("age", "workclass", "fnlwgt", "education", "edu_num", "marital", "occupation", 
                  "relationship", "race", "sex", "cap_gain", "cap_loss", 
                  "hours_week", "native_country", "income")


#Rename modalities
dd$workclass <- recode(dd$workclass, 
                       "Private" = "Priv",
                       "Self-emp-not-inc" = "SelfN",
                       "Self-emp-inc" = "SelfI",
                       "Federal-gov" = "Fed",
                       "Local-gov" = "Loc",
                       "State-gov" = "State",
                       "Without-pay" = "NoPay",
                       "Never-worked" = "NoPay")

dd$marital <- recode(dd$marital,
                     "Never-married" = "NevMarr",
                     "Married-civ-spouse" = "Married",
                     "Married-AF-spouse" = "Married",
                     "Married-spouse-absent" = "Sep",
                     "Separated" = "Sep",
                     "Divorced" = "Div",
                     "Widowed" = "Widow")

dd$occupation <- recode(dd$occupation,
                        "Exec-managerial" = "ExecMan",
                        "Prof-specialty" = "Prof",
                        "Adm-clerical" = "AdminCler",
                        "Sales" = "Sales",
                        "Craft-repair" = "CraftRep",
                        "Transport-moving" = "Trans",
                        "Handlers-cleaners" = "HandlCl",
                        "Machine-op-inspct" = "MachOp",
                        "Tech-support" = "Tech",
                        "Protective-serv" = "ProtServ",
                        "Armed-Forces" = "Army",
                        "Farming-fishing" = "FarmFish",
                        "Priv-house-serv" = "House",
                        "Other-service" = "Other")

dd$native_country <- recode(dd$native_country,
                            "United-States" = "USA",
                            .default = "Other")  # Group all other countries as "Other"


#Check outliers using the IQR method
find_outliers <- function(column) {
  Q1 <- quantile(column, 0.25, na.rm=TRUE)
  Q3 <- quantile(column, 0.75, na.rm=TRUE)
  IQR_value <- Q3 - Q1
  lower_bound <- Q1 - 1.5 * IQR_value
  upper_bound <- Q3 + 1.5 * IQR_value
  return(column[column < lower_bound | column > upper_bound])  # Return outliers
}

numeric_cols <- c("age", "edu_num", "cap_gain", "cap_loss", "hours_week")
outliers_list <- lapply(dd[numeric_cols], find_outliers)  # Apply to all numeric columns
outliers_list


#a function to find the mode (most frequent value)
fill_mode <- function(x) {
  mode_value <- names(sort(table(x), decreasing=TRUE))[1]  # Get the most frequent value
  x[is.na(x)| x == "?"] <- mode_value  # Replace NA with mode
  return(x)
}


#we apply it to columns with missing values
dd$workclass <- fill_mode(dd$workclass)
dd$occupation <- fill_mode(dd$occupation)
dd$native_country <- fill_mode(dd$native_country)

#we drop education (the same information is found in the column educational.num (in numbers))
dd <- subset(dd, select = -c(education))

class(dd[,1])
for ( i in 1:14){
  if(is.numeric(dd[,i])){
    hist(dd[,i],main=paste("hist of", names(dd)[i]))
    boxplot(dd[,i],main=paste("boxplot of", names(dd)[i]))
    print(table(dd[,i]))
    print(summary(dd[,i]))
  } else{
    print(table(dd[,i]))
    barplot(table(dd[,i]),main=paste("barplot of", names(dd)[i]))
    pie(table(dd[,i]),main=paste("pie of", names(dd)[i]))
  }
}






# RESIDUAL ANANLYSIS


df <- read.csv("/adult_def.csv")
modelo <- lm(income_integer ~ ., data = df)
plot(modelo$fitted.values, resid(modelo),
     xlab = "Valores predichos", ylab = "Residuos",
     main = "Residuos vs Valores Predichos")
abline(h = 0, col = "red")

hist(resid(modelo), main = "Histograma de residuos", xlab = "Residuos")

qqnorm(resid(modelo))
qqline(resid(modelo), col = "red")

# RESIDUAL ANANLYSIS TRANSFORMED

btmodel <- lm(y_trans ~ agebt + edu_num_bt + cap_gain + cap_loss + hours_week, data = dd)

plot(btmodel$fitted.values, resid(btmodel),
     xlab = "Valores predichos", ylab = "Residuos",
     main = "Residuos vs Valores Predichos")
abline(h = 0, col = "red")

hist(resid(btmodel), main = "Histograma de residuos", xlab = "Residuos")

qqnorm(resid(btmodel))
qqline(resid(btmodel), col = "red")

# MULTICOLLINEARITY

library(car)

vif(modelo)

# MULTICOLLINEARITY TRANSFORMED

vif(btmodel)

# EXPLORING NON-LINEAR RELATIONSHIPS

df$income_bin <- ifelse(df$income == ">50K", 1, 0)

library(dplyr)
library(ggplot2)

plot_log_odds <- function(df, var, response = "income") {
  df %>%
    mutate(bin = ntile(.data[[var]], 20)) %>%  # agrupa la variable numérica en 20 grupos
    group_by(bin) %>%
    summarise(
      x = mean(.data[[var]], na.rm = TRUE),
      p = mean(.data[[response]], na.rm = TRUE),
      logit = log(p / (1 - p))
    ) %>%
    ggplot(aes(x = x, y = logit)) +
    geom_point() +
    geom_smooth(method = "loess", se = FALSE, color = "blue") +
    labs(
      title = paste("Log-odds vs", var),
      x = var,
      y = "Log-odds"
    ) +
    theme_minimal()
}

plot_log_odds(df, "age")
plot_log_odds(df, "fnlwgt")
plot_log_odds(df, "edu_num")
plot_log_odds(df, "hours_week")

library(splines)
df$hours_week_spline <- ns(df$hours_week, df = 4)
plot_log_odds(df, "hours_week")

df$fnlwgt_log <- log1p(df$fnlwgt)
plot_log_odds(df, "fnlwgt")


# Función robusta para log-odds con suavizado controlado

plot_log_odds <- function(df, var, response = "income") {
  df %>%
    mutate(bin = ntile(.data[[var]], 20)) %>%
    group_by(bin) %>%
    summarise(
      x = mean(.data[[var]], na.rm = TRUE),
      p = mean(.data[[response]], na.rm = TRUE),
      p = ifelse(p == 0, 0.001, ifelse(p == 1, 0.999, p)),  # evita log(0) y log(1)
      logit = log(p / (1 - p))
    ) %>%
    ggplot(aes(x = x, y = logit)) +
    geom_point(color = "darkred") +
    geom_line(color = "steelblue", linewidth = 1) +  # sin loess
    labs(
      title = paste("Log-odds vs", var),
      x = var,
      y = "Log-odds"
    ) +
    theme_minimal()
}

# Ejecutar para cap_gain y cap_loss
plot_log_odds(df, "cap_gain")
plot_log_odds(df, "cap_loss")

df$cap_gain <- log1p(df$cap_gain)
df$cap_loss <- log1p(df$cap_loss)

plot_log_odds(adult_def, "cap_gain")
plot_log_odds(adult_def, "cap_loss")

#GRAFICAS FINALES QUE APORTAN DE MÁS

m6 <- glm(income_bin ~ age + education_num + hours_per_week + capital_gain + capital_loss + fnlwgt, 
          data = adult_def, 
          family = binomial)

marginalModelPlots(m6)
avPlots(m6)
crPlots(m6)


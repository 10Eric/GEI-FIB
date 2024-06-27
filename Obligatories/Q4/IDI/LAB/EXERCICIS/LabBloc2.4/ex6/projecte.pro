QT           += opengl

CONFIG       += qt
TEMPLATE      = app

DEPENDPATH   +=.
INCLUDEPATH +=  /usr/include/glm
INCLUDEPATH += ./Model

FORMS        += MyForm.ui

HEADERS      += MyForm.h \
MyEuler.h MyLabel.h

SOURCES      += main.cpp    \
MyForm.cpp  \
MyEuler.cpp \
MyLabel.cpp \
./Model/model.cpp


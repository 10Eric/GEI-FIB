QT           += opengl

CONFIG       += qt
TEMPLATE      = app

DEPENDPATH   +=.
INCLUDEPATH +=  /usr/include/glm
INCLUDEPATH += ./Model

FORMS        += MyForm.ui

HEADERS      += MyForm.h \
MyEuler.h

SOURCES      += main.cpp    \
MyForm.cpp  \
MyEuler.cpp \
./Model/model.cpp


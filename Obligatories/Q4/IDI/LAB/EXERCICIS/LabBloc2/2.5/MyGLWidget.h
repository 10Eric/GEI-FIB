// MyGLWidget.h
#include "BL2GLWidget.h"
#include "model.h"

class MyGLWidget : public BL2GLWidget {
  Q_OBJECT

  public:
    MyGLWidget(QWidget *parent=0) : BL2GLWidget(parent) {}
    ~MyGLWidget();

  private:
    int printOglError(const char file[], int line, const char func[]);
    virtual void carregaShaders();
    virtual void initializeGL ();
    virtual void paintGL ();
    virtual void creaBuffers();
    virtual void keyPressEvent(QKeyEvent* event);
    virtual void modelTransform ();
    void projectTransform ();
    void viewTransform ();
    GLuint projLoc,viewLoc,VAO1;
    Model m;
    float rotacio = 0.0;
};

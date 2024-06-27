// MyGLWidget.h
#include "BL3GLWidget.h"

class MyGLWidget : public BL3GLWidget {
  Q_OBJECT

  public:
    MyGLWidget(QWidget *parent=0) : BL3GLWidget(parent) {}
    ~MyGLWidget();

  protected:
    virtual void mouseMoveEvent(QMouseEvent *e);
    virtual void keyPressEvent(QKeyEvent* event);
    virtual void iniMaterialTerra ();
    virtual void carregaShaders();
    virtual void initializeGL ();
    virtual  void modelTransformPatricio ();

  private:
    int printOglError(const char file[], int line, const char func[]);
    GLuint posFoc,posFoc1, colorFoc,camaras, escenes;
    glm::vec3 posPat = glm::vec3(0,-0.85,0);
    glm::vec3 f = glm::vec3(0.8, 0.8, 0.0);
    glm::vec4 rr = glm::vec4(0, -0.5, 0, 1);
    glm::vec4 rr1 = glm::vec4(1, 1, 1, 1);
    int camara = 1;
    int escena = 1;
    int gl = 0;
};

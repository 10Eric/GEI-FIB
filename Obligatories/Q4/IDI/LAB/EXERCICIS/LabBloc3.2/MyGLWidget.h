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

  private:
    int printOglError(const char file[], int line, const char func[]);
    GLuint posFoc, colorFoc,camaras;
    glm::vec3 f = glm::vec3(0.8, 0.8, 0.8);
    glm::vec4 rr = glm::vec4(0, 0, 0, 1);
    int camara = 0;
};

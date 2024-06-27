#include "MyLabel.h"
// constructor
MyLabel::MyLabel(QWidget *parent)
: QLabel(parent) {
// Inicialització d'atributs si cal
}
// implementació slots
void MyLabel::returnPressed() {
    QString r = eltexto;
    r.truncate(valor);
    setText(r);
}

void MyLabel::tractaText (const QString &s) {
    eltexto = s;
    returnPressed();
}
void MyLabel::tractaSlider(int x){
    valor = x;
    returnPressed();
}
#include <QLabel>
class MyLabel: public QLabel
{
    Q_OBJECT

public:
MyLabel (QWidget *parent);

public slots:
void tractaText (const QString &s);
void tractaSlider(int);
void returnPressed ();

private:
    QString eltexto;
    int valor = 99;
};
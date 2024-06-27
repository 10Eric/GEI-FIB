/********************************************************************************
** Form generated from reading UI file 'MyForm.ui'
**
** Created by: Qt User Interface Compiler version 5.15.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MYFORM_H
#define UI_MYFORM_H

#include <MyLabel.h>
#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDial>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QSlider>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>
#include "MyEuler.h"

QT_BEGIN_NAMESPACE

class Ui_MyForm
{
public:
    QWidget *layoutWidget;
    QHBoxLayout *horizontalLayout;
    MyEuler *myForm;
    QSpacerItem *horizontalSpacer;
    QWidget *layoutWidget1;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QSlider *horizontalSlider;
    QLabel *label_2;
    QSlider *horizontalSlider_2;
    QLabel *label_3;
    QSlider *horizontalSlider_3;
    QSpinBox *spinBox;
    QRadioButton *radioButton;
    QRadioButton *radioButton_2;
    QLabel *label_4;
    QDial *dial_2;
    QLabel *label_5;
    QDial *dial;
    QLabel *label_7;
    QSpinBox *spinBox_2;
    QLabel *label_8;
    QSpinBox *spinBox_3;
    QLabel *label_9;
    QSpinBox *spinBox_4;
    MyLabel *label_6;
    QPushButton *pushButton;

    void setupUi(QWidget *MyForm)
    {
        if (MyForm->objectName().isEmpty())
            MyForm->setObjectName(QString::fromUtf8("MyForm"));
        MyForm->resize(983, 773);
        layoutWidget = new QWidget(MyForm);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(0, 0, 751, 670));
        horizontalLayout = new QHBoxLayout(layoutWidget);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        myForm = new MyEuler(layoutWidget);
        myForm->setObjectName(QString::fromUtf8("myForm"));
        myForm->setMinimumSize(QSize(691, 668));

        horizontalLayout->addWidget(myForm);

        horizontalSpacer = new QSpacerItem(48, 17, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        layoutWidget1 = new QWidget(MyForm);
        layoutWidget1->setObjectName(QString::fromUtf8("layoutWidget1"));
        layoutWidget1->setGeometry(QRect(762, 2, 165, 753));
        verticalLayout = new QVBoxLayout(layoutWidget1);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        label = new QLabel(layoutWidget1);
        label->setObjectName(QString::fromUtf8("label"));

        verticalLayout->addWidget(label);

        horizontalSlider = new QSlider(layoutWidget1);
        horizontalSlider->setObjectName(QString::fromUtf8("horizontalSlider"));
        horizontalSlider->setMinimumSize(QSize(160, 37));
        horizontalSlider->setMinimum(-360);
        horizontalSlider->setMaximum(360);
        horizontalSlider->setOrientation(Qt::Horizontal);

        verticalLayout->addWidget(horizontalSlider);

        label_2 = new QLabel(layoutWidget1);
        label_2->setObjectName(QString::fromUtf8("label_2"));

        verticalLayout->addWidget(label_2);

        horizontalSlider_2 = new QSlider(layoutWidget1);
        horizontalSlider_2->setObjectName(QString::fromUtf8("horizontalSlider_2"));
        horizontalSlider_2->setMinimum(-360);
        horizontalSlider_2->setMaximum(360);
        horizontalSlider_2->setOrientation(Qt::Horizontal);

        verticalLayout->addWidget(horizontalSlider_2);

        label_3 = new QLabel(layoutWidget1);
        label_3->setObjectName(QString::fromUtf8("label_3"));

        verticalLayout->addWidget(label_3);

        horizontalSlider_3 = new QSlider(layoutWidget1);
        horizontalSlider_3->setObjectName(QString::fromUtf8("horizontalSlider_3"));
        horizontalSlider_3->setMinimum(0);
        horizontalSlider_3->setMaximum(180);
        horizontalSlider_3->setValue(90);
        horizontalSlider_3->setOrientation(Qt::Horizontal);

        verticalLayout->addWidget(horizontalSlider_3);

        spinBox = new QSpinBox(layoutWidget1);
        spinBox->setObjectName(QString::fromUtf8("spinBox"));
        spinBox->setMinimum(0);
        spinBox->setMaximum(180);
        spinBox->setValue(90);

        verticalLayout->addWidget(spinBox);

        radioButton = new QRadioButton(layoutWidget1);
        radioButton->setObjectName(QString::fromUtf8("radioButton"));

        verticalLayout->addWidget(radioButton);

        radioButton_2 = new QRadioButton(layoutWidget1);
        radioButton_2->setObjectName(QString::fromUtf8("radioButton_2"));

        verticalLayout->addWidget(radioButton_2);

        label_4 = new QLabel(layoutWidget1);
        label_4->setObjectName(QString::fromUtf8("label_4"));

        verticalLayout->addWidget(label_4);

        dial_2 = new QDial(layoutWidget1);
        dial_2->setObjectName(QString::fromUtf8("dial_2"));
        dial_2->setMinimum(-360);
        dial_2->setMaximum(360);

        verticalLayout->addWidget(dial_2);

        label_5 = new QLabel(layoutWidget1);
        label_5->setObjectName(QString::fromUtf8("label_5"));

        verticalLayout->addWidget(label_5);

        dial = new QDial(layoutWidget1);
        dial->setObjectName(QString::fromUtf8("dial"));
        dial->setMinimum(-360);
        dial->setMaximum(360);

        verticalLayout->addWidget(dial);

        label_7 = new QLabel(layoutWidget1);
        label_7->setObjectName(QString::fromUtf8("label_7"));

        verticalLayout->addWidget(label_7);

        spinBox_2 = new QSpinBox(layoutWidget1);
        spinBox_2->setObjectName(QString::fromUtf8("spinBox_2"));
        spinBox_2->setMaximum(255);

        verticalLayout->addWidget(spinBox_2);

        label_8 = new QLabel(layoutWidget1);
        label_8->setObjectName(QString::fromUtf8("label_8"));

        verticalLayout->addWidget(label_8);

        spinBox_3 = new QSpinBox(layoutWidget1);
        spinBox_3->setObjectName(QString::fromUtf8("spinBox_3"));
        spinBox_3->setMaximum(255);

        verticalLayout->addWidget(spinBox_3);

        label_9 = new QLabel(layoutWidget1);
        label_9->setObjectName(QString::fromUtf8("label_9"));

        verticalLayout->addWidget(label_9);

        spinBox_4 = new QSpinBox(layoutWidget1);
        spinBox_4->setObjectName(QString::fromUtf8("spinBox_4"));
        spinBox_4->setMaximum(255);

        verticalLayout->addWidget(spinBox_4);

        label_6 = new MyLabel(layoutWidget1);
        label_6->setObjectName(QString::fromUtf8("label_6"));
        label_6->setMinimumSize(QSize(0, 16));

        verticalLayout->addWidget(label_6);

        pushButton = new QPushButton(layoutWidget1);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        QSizePolicy sizePolicy(QSizePolicy::Fixed, QSizePolicy::Fixed);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(pushButton->sizePolicy().hasHeightForWidth());
        pushButton->setSizePolicy(sizePolicy);
        pushButton->setStyleSheet(QString::fromUtf8("background-color:red"));

        verticalLayout->addWidget(pushButton);


        retranslateUi(MyForm);
        QObject::connect(pushButton, SIGNAL(clicked()), MyForm, SLOT(close()));
        QObject::connect(horizontalSlider, SIGNAL(valueChanged(int)), myForm, SLOT(psiAngle(int)));
        QObject::connect(horizontalSlider_2, SIGNAL(valueChanged(int)), myForm, SLOT(thetaAngle(int)));
        QObject::connect(horizontalSlider_3, SIGNAL(valueChanged(int)), myForm, SLOT(fovAngle(int)));
        QObject::connect(spinBox, SIGNAL(valueChanged(int)), myForm, SLOT(fovAngle(int)));
        QObject::connect(horizontalSlider_3, SIGNAL(valueChanged(int)), spinBox, SLOT(setValue(int)));
        QObject::connect(spinBox, SIGNAL(valueChanged(int)), horizontalSlider_3, SLOT(setValue(int)));
        QObject::connect(radioButton_2, SIGNAL(clicked(bool)), radioButton, SLOT(repaint()));
        QObject::connect(radioButton, SIGNAL(clicked(bool)), radioButton_2, SLOT(repaint()));
        QObject::connect(radioButton, SIGNAL(clicked(bool)), myForm, SLOT(pintaPatricio()));
        QObject::connect(radioButton_2, SIGNAL(clicked(bool)), myForm, SLOT(pintaLegoman()));
        QObject::connect(dial, SIGNAL(valueChanged(int)), myForm, SLOT(psiAngle(int)));
        QObject::connect(dial_2, SIGNAL(valueChanged(int)), myForm, SLOT(thetaAngle(int)));
        QObject::connect(myForm, SIGNAL(setPsi(int)), dial, SLOT(setValue(int)));
        QObject::connect(myForm, SIGNAL(setTheta(int)), dial_2, SLOT(setValue(int)));
        QObject::connect(spinBox_2, SIGNAL(valueChanged(int)), label_6, SLOT(pintaR(int)));
        QObject::connect(spinBox_3, SIGNAL(valueChanged(int)), label_6, SLOT(pintaG(int)));
        QObject::connect(spinBox_4, SIGNAL(valueChanged(int)), label_6, SLOT(pintaB(int)));
        QObject::connect(spinBox_2, SIGNAL(valueChanged(int)), myForm, SLOT(actualitzaR(int)));
        QObject::connect(spinBox_3, SIGNAL(valueChanged(int)), myForm, SLOT(actualitzaG(int)));
        QObject::connect(spinBox_4, SIGNAL(valueChanged(int)), myForm, SLOT(actualitzaB(int)));

        QMetaObject::connectSlotsByName(MyForm);
    } // setupUi

    void retranslateUi(QWidget *MyForm)
    {
        MyForm->setWindowTitle(QCoreApplication::translate("MyForm", "Form", nullptr));
        label->setText(QCoreApplication::translate("MyForm", "Gir eix Y", nullptr));
        label_2->setText(QCoreApplication::translate("MyForm", "Gir eix X", nullptr));
        label_3->setText(QCoreApplication::translate("MyForm", "Angle FOV", nullptr));
        radioButton->setText(QCoreApplication::translate("MyForm", "Patricio", nullptr));
        radioButton_2->setText(QCoreApplication::translate("MyForm", "Legoman", nullptr));
        label_4->setText(QCoreApplication::translate("MyForm", "Gir eix Y", nullptr));
        label_5->setText(QCoreApplication::translate("MyForm", "Gir eix X", nullptr));
        label_7->setText(QCoreApplication::translate("MyForm", "R", nullptr));
        label_8->setText(QCoreApplication::translate("MyForm", "G", nullptr));
        label_9->setText(QCoreApplication::translate("MyForm", "B", nullptr));
        label_6->setText(QString());
        pushButton->setText(QCoreApplication::translate("MyForm", "&Sortir", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MyForm: public Ui_MyForm {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MYFORM_H

#version 330 core

in vec3 vertex;
in vec3 normal;

in vec3 matamb;
in vec3 matdiff;
in vec3 matspec;
in float matshin;

out vec3 matambFS;
out vec3 matdiffFS;
out vec3 matspecFS;
out float matshinFS;

uniform mat4 proj;
uniform mat4 view;
uniform mat4 TG;

out vec4 vertSCO;
out vec3 NMNormal;


void main()
{	
    matambFS = matamb;
    matdiffFS = matdiff;
    matspecFS = matspec;
    matshinFS = matshin;
    vertSCO = (view * TG * vec4(vertex,1.0));
    mat3 NormalMatrix = inverse (transpose (mat3 (view * TG)));
    NMNormal = NormalMatrix * normal;
    NMNormal = normalize(NMNormal);
    gl_Position = proj * vertSCO;
}
#version 330 core

out vec4 FragColor;

void main() {
    if ((int(gl_FragCoord.y)/5)%2 == 0) discard;

    else {
        if (gl_FragCoord.x < 354.0 && gl_FragCoord.y > 354.0) FragColor = vec4(1.0,0.0,0.0,1);
        else if (gl_FragCoord.x > 354.0 && gl_FragCoord.y > 354.0) FragColor = vec4(0.0,0.0,1.0,1);
        else if (gl_FragCoord.x < 354.0 && gl_FragCoord.y < 354.0) FragColor = vec4(1.0,1.0,0.0,1);
        else if (gl_FragCoord.x > 354.0 && gl_FragCoord.y < 354.0) FragColor = vec4(0.0,1.0,0.0,1);
    }
}


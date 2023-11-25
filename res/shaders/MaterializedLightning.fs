#version 460
const float SPECULAR_EXPONENT = 5.0;


struct Attenuation {
    float constant;
    float linear;
    float exponent;
};

struct PointLight {
    vec3 colour;
    vec3 position;
    float intensity;
    Attenuation att;
};

struct Material {
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    float reflectance;
};

//Inputs
in vec2 pass_uvs;
in vec3 mvVertexNormal;
in vec3 mvVertexPos;

//Output
out vec4 fragColor;

//Uniforms
uniform sampler2D textureSampler;
uniform bool usesTexture;
uniform vec3 ambientLight;
uniform float specularPower;
uniform vec3 camera_pos;
uniform PointLight pointLight;
uniform Material material;


vec4 ambientC;
vec4 diffuseC;
vec4 specularC;


void setupColours(Material material, vec2 textCoord) {
    if (usesTexture) {
        ambientC = texture(textureSampler, textCoord);
        diffuseC = ambientC;
        specularC = ambientC;
    } else {
        ambientC = material.ambient;
        diffuseC = material.diffuse;
        specularC = material.specular;
    }
}


vec4 calculateDiffuse(PointLight light, vec3 position, vec3 normal) {
    vec3 lightDirection = normalize(light.position - position);
    float diffuseFactor = max(dot(normal, lightDirection), 0);
    return diffuseC * vec4(light.colour, 1.0) * light.intensity * diffuseFactor;
}


vec4 calculateSpecular(PointLight light, vec3 position, vec3 normal) {
    vec3 lightDirection = normalize(light.position - position);
    vec3 toLightSource = normalize(lightDirection);
    vec3 cameraDirection = normalize(-position);
    vec3 reflectedLight = normalize(reflect(-toLightSource, normal));

    float specularFactor = max(dot(cameraDirection, reflectedLight), 0.0);
    specularFactor = pow(specularFactor, SPECULAR_EXPONENT);

    return specularC * specularFactor * material.reflectance * vec4(light.colour, 1.0);
}


float calculateAttenuation(PointLight light, float distance) {
    return 1.0 / (light.att.constant + light.att.linear * distance + light.att.exponent * distance * distance);
}


void main() {
    setupColours(material, pass_uvs);


    vec4 diffuseComponent = calculateDiffuse(pointLight, mvVertexPos, mvVertexNormal);
    vec4 specularComponent = calculateSpecular(pointLight, mvVertexPos, mvVertexNormal);


    float distanceToLight = length(pointLight.position - mvVertexPos);
    float attenuation = calculateAttenuation(pointLight, distanceToLight);


    fragColor = ambientC * vec4(ambientLight, 1) + (diffuseComponent + specularComponent) * attenuation;
}
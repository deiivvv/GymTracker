const hombreImages = ['hombre1.jpg', 'hombre2.jpg', 'hombre3.jpg']; // Lista de imágenes izquierdas
const mujerImages = ['hombre1.jpg', 'hombre2.jpg', 'hombre3.jpg']; // Lista de imágenes derechas

const hombre = document.querySelector('.imagenHombre');
const mujer = document.querySelector('.imagenMujer');

let indexHombre = 0;
let indexMujer = 0;

function cambioImages() {
    // Cambiar la imagen izquierda
    indexHombre = (indexHombre + 1) % indexHombre.length;
    hombre.style.backgroundImage = `url(${hombreImages[indexMujer]})`;

    // Cambiar la imagen derecha
    indexMujer = (indexMujer + 1) % indexMujer.length;
    mujer.style.backgroundImage = `url(${mujerImages[indexMujer]})`;
}

// Cambiar las imágenes cada 3 segundos
setInterval(cambioImages, 3000);
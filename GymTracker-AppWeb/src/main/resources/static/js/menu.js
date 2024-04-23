const hombreImages = ['hombre1.png', 'hombre2.png'];
const mujerImages = ['hombre1.png', 'hombre2.png']; 

let index = 0;

function cambioImages() {

    if(index >= hombreImages.length || index >= mujerImages.length){
        index = 0;
    }
    document.getElementById("idImagenHombre").src= "./images/menu/hombre/" + hombreImages[index];
    document.getElementById("idImagenMujer").src= "./images/menu/hombre/" + mujerImages[index];

    index++;

}

setInterval(cambioImages, 3000);
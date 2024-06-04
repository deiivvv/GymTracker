function clase_activo(){
    let url = window.location.href;
    let li=document.getElementById(url.split("/")[3])
    li.style="background-color: #198754;" + "border-color: #146c43;";
    li.querySelector("a").style="color: #fff;";
}

window.onload=function() {
    

}

function requestFeed(id) {
    var xmlRequest=new XMLHttpRequest();
    console.log("hello");
    xmlRequest.onreadystatechange=function() {

        if(xmlRequest.readyState==4&&xmlRequest.status==200) {
            //console.log(xmlRequest.responseText);
            var obj=JSON.parse(xmlRequest.responseText);
            updatefeed(obj);
        }
    }
    xmlRequest.open('GET',id,true);
    xmlRequest.send();


}

document.getElementById('nav-button').addEventListener('click',function(event) {
    console.log(event.srcElement.id);
    if(event.srcElement.id.includes('news')) {
    requestFeed(event.srcElement.id);
    }
});

function updatefeed(obj) {
    var fragment=document.createDocumentFragment();
    var ul=document.createElement('UL');
    for(var i=0;i<5;i++) {
        var li=document.createElement('li');
        li.innerHTML=obj[i].description;
        ul.appendChild(li);
}
    ul.classList.add('listItems');
    fragment.appendChild(ul);
    var element1=document.querySelector('.top-stories');
    var element=document.querySelector('.listItems');
    console.log("hello1");
    element.remove();
    element1.appendChild(fragment);
    
}
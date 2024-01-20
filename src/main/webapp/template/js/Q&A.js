var quesItem = document.querySelectorAll('.content-main-item')

quesItem.forEach((item, index)=>{
    Object.assign(item.children[4].style, {
        transition: "0.3s"
    })
    item.children[3].addEventListener('click',function () {
        item.children[3].classList.add('hide')
        item.children[4].classList.remove('hide')
    })

    item.children[4].children[2].addEventListener('click', function (){
        item.children[3].classList.remove('hide')
        item.children[4].classList.add('hide')
    })
})
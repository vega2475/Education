//Стрелочная функция
const a = () => {
    console.log("Dima")
}
objA = {
    a: 10
}
const str = 'person'

objA[str] = 'David'
delete objA.person

console.log(objA)

globalThis.console.log("uwu")

//from json / to json
objJSON = {
    title: "myObj",
    condition: true
}
let link = JSON.stringify(objJSON)
console.log(link)
console.log(JSON.parse(link))
objJSON2 = Object.assign({age: 25}, objJSON)
objJSON2.title = 1;
console.log(objJSON2)
console.log(objJSON)
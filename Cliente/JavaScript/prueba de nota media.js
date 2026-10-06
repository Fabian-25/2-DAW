const alumnos = [
    {nombre: "Ana", nota: 7},
    {nombre: "Luis", nota: 5},
    {nombre: "Eva", nota: 9},
    {nombre: "Pablo", nota: 3}
];

const media = alumnos.reduce(
    (acumulador, alumnos) => acumulador + alumnos.nota,
    0   
);

console.log(media/alumnos.length);
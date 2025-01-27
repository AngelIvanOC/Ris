//1. Crea una base de datos llamada pokedex.
use pokedex5A;

//2. Crea una colección llamada pokemon.
db.createCollection("pokemon");

//3. Inserta los documentos que encontrarás en el archivo data.json adjunto a la tarea asignada en Classroom.
db.pokemon.insertMany(
[
  {
    "id": 1,
    "name": {
      "english": "Bulbasaur",
      "japanese": "フシギダネ",
      "chinese": "妙蛙种子",
      "french": "Bulbizarre"
    },
    "type": ["Grass", "Poison"],
    "base": {
      "HP": 45,
      "Attack": 49,
      "Defense": 49,
      "Sp. Attack": 65,
      "Sp. Defense": 65,
      "Speed": 45
    },
    "species": "Seed Pokémon",
    "description": "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger.",
    "evolution": { "next": [["2", "Level 16"]] },
    "profile": {
      "height": "0.7 m",
      "weight": "6.9 kg",
      "egg": ["Monster", "Grass"],
      "ability": [
        ["Overgrow", "false"],
        ["Chlorophyll", "true"]
      ],
      "gender": "87.5:12.5"
    },
    "image": {
      "sprite": "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/sprites/001.png",
      "thumbnail": "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/thumbnails/001.png",
      "hires": "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/hires/001.png"
    }
  },
  {
    "id": 2,
    "name": {
      "english": "Ivysaur",
      "japanese": "フシギソウ",
      "chinese": "妙蛙草",
      "french": "Herbizarre"
    },
    "type": ["Grass", "Poison"],
    "base": {
      "HP": 60,
      "Attack": 62,
      "Defense": 63,
      "Sp. Attack": 80,
      "Sp. Defense": 80,
      "Speed": 60
    },
    "species": "Seed Pokémon",
    "description": "There is a bud on this Pokémon’s back. To support its weight, Ivysaur’s legs and trunk grow thick and strong. If it starts spending more time lying in the sunlight, it’s a sign that the bud will bloom into a large flower soon.",
    "evolution": { "prev": ["1", "Level 16"], "next": [["3", "Level 32"]] },
    "profile": {
      "height": "1 m",
      "weight": "13 kg",
      "egg": ["Monster", "Grass"],
      "ability": [
        ["Overgrow", "false"],
        ["Chlorophyll", "true"]
      ],
      "gender": "87.5:12.5"
    },
    "image": {
      "sprite": "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/sprites/002.png",
      "thumbnail": "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/thumbnails/002.png",
      "hires": "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master/images/pokedex/hires/002.png"
    }
  }
  
//4. Escribe una consulta para responder a las siguientes preguntas:
db.pokemon.find();
  //a. ¿Cuántas especies de Pokémon distintas existen?
  db.pokemon.distinct("species").length
  //b. ¿Cuántos Pokémon tienen la palabra mercy en su descripción?
  db.pokemon.find({description: {$regex: "mercy"}}).count()
  //c. ¿Cuál es el nombre de los Pokémon cuya especie es Egg Pokémon?
  db.pokemon.find({species: "Egg Pokémon"}, {name: 1, _id: 0});
  //d. ¿Cuál es el segundo Pokémon más pesado (weight)?
  const cursor = db.pokemon.find({}, {name: 1, _id: 0}).sort({"profile.weight": -1}).toArray();
  print(cursor[0].name);
  //e. ¿Cuáles son los Pokémon cuyo ataque especial (Sp. Attack) se encuentra entre 50 y 100?

  //f. ¿Qué Pokémon tienen la misma cantidad de ataque y defensa?
  db.pokemon.find({$expr: { $eq: ["$base.Attack", "$base.Defense"]}}, {name: 1, _id: 0})
  //g. ¿Cuál es el Pokémon con mayor cantidad de habilidades (abilities)?
  db.pokemon.find({}, {name: 1, _id: 0}).sort({"profile.ability": 1}).limit(1);
  //5. Actualiza los Pokémon de la especie Dragon Pokémon, añadiéndoles un atributo llamado region con el valor Unova.
  db.pokemon.updateMany({species: "Dragon Pokémon"}, {$push: {region: "Unova"}});
  //6. Actualiza el documento correspondiente a Greninja, retirándole el tipo Dark.
  db.pokemon.updateOne({"name.english": "Greninja"}, {$pop: {type: 1}});
  //7. Añade un nuevo documento del Pokémon que prefieras. Si no conoces información al respecto, invéntala considerando los atributos de los documentos previos.
  db.pokemon.insertOne({
    "id": 899,
    "name": {
      "english": "Uxue",
      "english": "Sword Pokémon"
    },
    "type": ["Comedor", "Estudiante"],
    "species": "Debil Pokémon",
    "description": "Este pokemon va a la escuela.",
    "evolution": {},
    "profile": {
      "height": "2 m",
      "weight": "100 kg",
      "ability": [["Saltar", "false"]]
    },
    "image": {
      "sprite": "https://nose.png",
      "thumbnail": "https://nose2.png",
      "hires": "https://utez.edu.mx"
    }
  })
  //8. Elimina el documento que insertaste, utilizando su identificador.
  db.pokemon.deleteOne({id: 899});
  //9. Elimina todos los Pokémon que contengan Sword Pokémon en el nombre de su especie.
  db.pokemon.deleteMany({"name.english": {$regex: "^Sword Pokémon"}})
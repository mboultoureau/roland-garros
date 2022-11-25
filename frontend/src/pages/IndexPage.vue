<template>
  <div class="relative overflow-hidden">
    <header class="flex flex-col justify-center">
      <div class="leading-none text_RG">ROLAND</div>
      <div class="leading-none text_RG">GARROS</div>
    </header>
    <figure>
    <img
      class="image1 absolute -bottom-5 right-0"
      src="http://localhost:8000/img/casper_ruud.png"
    />
    </figure>
    <img
      class="image2 absolute bottom-0 right-56"
      src="http://localhost:8000/img/rafael_nadal.png"
    />
  </div>
  <div class="bg-white marge">

    <div class="image-with-text">
    <div class="image-side">
      <img src="https://images.prismic.io/fft-rg-commun-news/54830822-9f37-4e2b-89a8-f3ea1945f43f_20220523_RG_NG2_4545_web.jpg?auto=compress,format&w=1000&h=800">
    </div>

    <div class="text-side">
      <div class="text-side-inline">
        <p class="text-side-title">Qu'est ce que Roland Garros ?</p>
        <p class="text-side-desc">
          Les Internationaux de France, ou tournoi de Roland-Garros, ou plus simplement Roland-Garros par métonymie, est un tournoi de tennis sur terre battue créé en 1925 et qui se tient annuellement depuis 1928 à Paris, dans le stade Roland-Garros. Il succède au Championnat de France de tennis créé en 1891.
        </p>

        <a href="/">
          <div class="text-side-button">Voir les joueurs</div>
        </a>
      </div>
    </div>
  </div>

</div>
<div class="bg-gray-200">
  <div>
      <div class="count-items">
        <div>
          <div id="value">{{ nbPlayers }}</div>
          <p>Joueurs</p>
        </div>

        <div>
          <div id="value1">{{ nbTerrains }}</div>
          <p>Terrains</p>
        </div>

        <div>
          <div id="value2">{{ nbMatches }}</div>
          <p>Matchs</p>
        </div>

        <div>
          <div id="value3">{{ nbDays }}</div>
          <p>Jours</p>
        </div>

      </div>
    </div>


</div>
<div class="bg-white marge">

  <div class="image-with-text">
    <div class="text-side">
      <div class="text-side-inline">
        <p class="text-side-title">La carrière de Serena en images</p>
        <p class="text-side-desc">

73 titres en simple dont 23 en Grand Chelem, 23 titres en double dames dont 14 Grands Chelems, 4 médailles d'or olympique et 319 semaines passées au sommet du tennis mondial en 27 ans de carrière, voici un aperçu en images de la fabuleuse odyssée de Serena Williams.
        </p>
        <a href="/">
          <div class="text-side-button">Voir plus</div>
        </a>
      </div>
    </div>
    <div class="image-side">
      <img src="https://images.prismic.io/fft-rg-site/5bffa621-f312-4ba8-a0cb-ab5a834f24f0_Serena+Williams+-+Victoire+Roland-Garros+2015+-+Corinne+Dubreuil+FFT.jpg?auto=compress,format&rect=0,136,3000,1860&w=645&h=400">
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'IndexPage',
  data: () => {
    return {
      nbDays: 0,
      nbMatches: 0,
      nbPlayers: 0,
      nbTerrains: 0
    }
  },
  mounted() {
    function animateNumber(callback, from, to, duration) {
      let start = null,
        animate = timestamp => {
          start = start || timestamp;
          let progress = Math.min((timestamp - start) / duration, 1);
          callback(progress * (to - from) + from);
          if(progress < 1) {
            window.requestAnimationFrame(animate);
          }
        };
      window.requestAnimationFrame(animate);
    }

    animateNumber(value => {
      document.getElementById("value").textContent = Math.floor(value);
    }, 0, 150, 5000);

    animateNumber(value => {
      document.getElementById("value1").textContent = Math.floor(value);
    }, 0, 27, 5000);

    animateNumber(value => {
      document.getElementById("value2").textContent = Math.floor(value);
    }, 0, 640, 5000);

    animateNumber(value => {
      document.getElementById("value3").textContent = Math.floor(value);
    }, 0, 43, 5000);
  }
}
</script>

<style lang="scss" scoped>
header {
  height: calc(100vh - 96px);
  background-image: url('http://localhost:8000/img/background.jpg');

  clip-path: polygon(0% 0%, 70.9% 0%, 100% 100%, 0% 100%);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: right;

  font-size: 9rem;
  color: white;
  margin: 0;
  padding: 0;
}

.text_RG {
  margin-left: 30px;
}

.image1 {
  height: calc(100% - 100px);
  // max-width: 50vw;
}
.image2 {
  height: calc(100% - 100px);
  // max-width: 50vw;
}

@media (max-width: 900px) {
  .text_RG {
    font-size: 5rem;
  }

  .image1, .image2 {
    width: 300px;
    height: auto !important;
  }
}


.image3 {
  width: 250px;
}
.count-items {
  text-align: center;
  font-size: 60px;
  font-weight: bold;
  font-feature-settings: 'tnum';
  color: #fff;
  > div {
    display: inline-block;
    margin: 1em 0.7em;
    padding: 1em;
    background: #c85919;
    border-radius: 5px;
    width: 4em;
    box-shadow: 10px 10px 0px 4px #c859198c, 5px 5px 0px 2px #c859198c;
    p {
      color: #fff;
      font-size: 30px;
    }
  }
}


/* DIV */
.blog-card {
  display: flex;
  flex-direction: column;
  margin: 1rem auto;
  box-shadow: 0 3px 7px -1px rgba(0, 0, 0, 0.1);
  margin-bottom: 1.6%;
  background: #fff;
  line-height: 1.4;
  font-family: sans-serif;
  border-radius: 5px;
  overflow: hidden;
  z-index: 0;
  box-shadow: 12px 12px 2px 1px #004f2f33;
}
.blog-card:hover .photo {
  transform: scale(1.3) rotate(3deg);
}
.blog-card .meta {
  position: relative;
  z-index: 0;
  height: 200px;
}
.blog-card .photo {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-size: cover;
  background-position: center;
  transition: transform 0.2s;
}
.blog-card .description {
  padding: 1rem;
  background: #fff;
  position: relative;
  z-index: 1;
}
.blog-card .description h1,
.blog-card .description h2 {
  font-family: Poppins, sans-serif;
}
.blog-card .description h1 {
  line-height: 1;
  margin: 0;
  font-size: 1.7rem;
}
.blog-card .description h2 {
  font-size: 1rem;
  font-weight: 300;
  text-transform: uppercase;
  color: #a2a2a2;
  margin-top: 5px;
}

.blog-card p {
  position: relative;
  margin: 1rem 0 0;
}
.blog-card p:first-of-type {
  margin-top: 1.25rem;
}
.blog-card p:first-of-type:before {
  content: "";
  position: absolute;
  height: 5px;
  background: #5ad67d;
  width: 35px;
  top: -0.75rem;
  border-radius: 3px;
}
.blog-card:hover .details {
  left: 0%;
}
@media (min-width: 640px) {
  .blog-card {
    flex-direction: row;
    max-width: 700px;
  }
  .blog-card .meta {
    flex-basis: 40%;
    height: auto;
  }
  .blog-card .description {
    flex-basis: 60%;
  }
  .blog-card .description:before {
    transform: skewX(-3deg);
    content: "";
    background: #fff;
    width: 30px;
    position: absolute;
    left: -10px;
    top: 0;
    bottom: 0;
    z-index: -1;
  }
}

/* TEXT + IMAGE*/

a {
  text-decoration: none;
  color: inherit;
}

.image-with-text {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin: auto;
}

.image-side {
  display: flex;
  width: auto;
  height: 400px;
}

.text-side {
  display: flex;
  width: calc(
    50% - 2em
      );
}

.text-side-inline {
  display: inline-block;
}

.text-side-title {
  color: #004f2f;
  font-weight: bold;
  font-size: 20px;
}

.text-side-desc {
  color: #58595b;
  font-size: 16px;
}

.text-side-button {
  transition: 0.3s all;
  background: #004f2f;
  padding: 15px 30px;
  color: #fff;
  width: max-content;
  max-width: 100%;
}

.text-side-button:hover {
  transition: 0.3s all;
  transform: translate(10px);
  opacity: 0.8;
}


@media (max-width: 768px) {
  .image-with-text {
    justify-content: center;
    width: auto;
    flex-direction: column;
    padding: 30px;
  }

  .image-side {
    width: auto;
  }

  .image-side img {
    max-width: 100%;
  }

  .text-side {
    width: auto;
  }
}

.marge{
  margin: 4em 0.7em;
}

</style>

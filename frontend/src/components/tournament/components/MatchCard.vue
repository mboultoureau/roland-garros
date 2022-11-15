<template>
  <div class="match-card w-96 my-2 rounded-md bg-gray-100">
    <div class="team-A flex items-center justify-between py-2 px-4">
      <div class="flex items-center">
        <div class="relative">
          <div class="border-2 border-gray-200 rounded-full overflow-hidden">
            <img class="w-12 h-12" src="https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400"/>
          </div>
          <img class="rounded-sm absolute bottom-0 -right-4 w-8 h-6" src="https://www.rolandgarros.com/img/flags-svg/NOR.svg" />
        </div>
        <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">
          R.Nadal (1)
        </div>
      </div>
          
      <div class="sets flex gap-4">
        <div class="font-bold"><q-badge color="green" rounded /></div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': true}">6</div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': false}">4</div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': true}">6</div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': true}">6</div>
      </div>
    </div>
    <hr class="mx-4 border-gray-300">
    <div class="team-A flex items-center justify-between py-2 px-4">
      <div class="flex items-center">
        <div class="relative">
          <div class="border-2 border-gray-200 rounded-full overflow-hidden">
            <img class="w-12 h-12" src="https://images.prismic.io/fft-rg-site/3fc68463-a645-45c3-9dd2-c639e724c7e2_20220309_PJ_RuudCasper_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400"/>
          </div>
          <img class="rounded-sm absolute bottom-0 -right-4 w-8 h-6" src="https://www.rolandgarros.com/img/flags-svg/AND.svg" />
        </div>
        <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': false}">
          R.Nadal (1)
        </div>
      </div>
      <div class="sets flex gap-4">
        <div class="font-bold text-tertiary" :class="{'text-secondary': false}">4</div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': true}">6</div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': false}">4</div>
        <div class="font-bold text-tertiary" :class="{'text-secondary': false}">4</div>
      </div>
    </div>
  </div>
</template>
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/pages/**/*.{html,vue,js}',
    './src/components/**/*.{html,vue,js}',
    './src/layouts/*.vue'
  ],
  theme: {
    extend: {
      colors: {
        primary: '#ea2d20',
        secondary: '#004f2f',
        tertiary: '#26a69a',
      },
    },
  },
  plugins: [],
}

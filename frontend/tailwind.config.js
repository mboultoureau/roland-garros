/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/pages/*/*.{html,js,vue}',
    './src/components/*.{html,js,vue}',
    './src/components/*/*.{html,js,vue}',
    './src/layouts/*.vue',
  ],
  theme: {
    extend: {
      colors: {
        primary: "#ea2d20",
        secondary: "#004f2f",
      },
    },
  },
  plugins: [],
}

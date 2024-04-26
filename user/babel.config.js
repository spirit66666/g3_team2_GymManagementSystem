module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  plugins: ["@babel/plugin-transform-private-methods"],
  proxyTable: {
    '/api': {
      target: 'http://localhost:9990',
      changeOrigin: true,
      pathRewrite: {
        '^/api': ''
      }
    }
  },

}

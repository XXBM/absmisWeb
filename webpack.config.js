module.exports = {
    entry: './src/main/resources/templates/reactjs/main.js', // 入口文件路径
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/, // babel 转换为兼容性的 js
                exclude: /node_modules/,
                loader: 'babel-loader',
                query: {
                    presets: ['react', 'latest']
                }
            }, {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }, {
                // 图片加载器
                test: /\.(png|jpg|gif|jpeg)$/,
                loader: 'url-loader?limit=2048'
            }
        ]
    }
}

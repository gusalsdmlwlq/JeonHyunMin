var electron = require('electron');
var BrowserWindow = electron.BrowserWindow;
var app = electron.app;
app.on('ready', function () {
    var appWindow; appWindow = new BrowserWindow({width: 1500,height: 1000});
    appWindow.loadURL('file://' + __dirname + '/index.html');
});

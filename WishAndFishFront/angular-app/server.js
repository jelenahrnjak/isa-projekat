  const express = require('express');
  const app1 = express(); 
  app1.disable("x-powered-by");

  app1.use(express.static('./dist/client'));

  app1.get('/*', function(req, res) {
    res.sendFile('index.html', {root: 'dist/client/'}
  );
  });

  app1.listen(process.env.PORT || 8080)
// Load the SDK for JavaScript
var AWS = require('aws-sdk');
// Set the region 
AWS.config.update({region: 'us-west-2'});

AWS.config.update({
  accessKeyId: "AKIAJ2CZSB4YJCO4MIWA",
  secretAccessKey: "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"
});

// Create S3 service object
s3 = new AWS.S3({apiVersion: '2006-03-01'});

// Call S3 to list the buckets
/*s3.listBuckets(function(err, data) {
  if (err) {
    console.log("Error", err);
  } else {
    console.log("Success", data.Buckets);
  }
});*/

var params = {
  Bucket: "bstuelp-bucket", 
  Key: "teste.xlsx"
};

s3.getObject(params, function(err, data) {
  if (err) {
    console.log(err, err.stack);
  } else {
    let dados = data
    let dadosFormatados = data.Body.toString()
    //console.log('buffer ');
    //console.log(data.Body);

    require('xlsx-populate').fromFileAsync(data.Body).then(workbook => {
      // Modify the workbook.
      const value = workbook.sheet("Masculino").cell("D11").value();
      // Log the value.
      console.log(value);
    });
  }
});

var file = require('fs').createWriteStream('./teste2.xlsx');
s3.getObject(params).createReadStream().pipe(file);

require('xlsx-populate').fromFileAsync('./teste2.zip').then(workbook => {
  // Modify the workbook.
  const value = workbook.sheet("Masculino").cell("D11").value();
  // Log the value.
  console.log(value);
});
<?php
require 'vendor/autoload.php';

use Zilic\Limun;
// https://flightphp.com/learn
// preporuka https://medium.com/@h.benkachoud/symfony-rest-api-without-fosrestbundle-and-using-jwt-authentication-part-1-944aa4faf946
Flight::route('GET /V1/vrhunski',function(){

    $limun = new Limun();

    Flight::json($limun->getLimuni());

});

Flight::map('notFound',function(){
    print_r($_SERVER);
   
});



Flight::start();
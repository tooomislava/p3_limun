<?php
namespace Zilic;

use stdClass;
use Faker\Factory;

class Limun
{
    public function getLimun()
    {
        $faker = \Faker\Factory::create();
        $limuni=[];
       

        for($i=1;$i<=27;$i++){
            $l = new stdClass();
            $l->sifra=$i;
            $l->zemlja=$faker->country;
            $datumRange = $faker->dateTimeBetween($startDate = '2020-06-10', $endDate = '2020-08-15', $timezone = null);
           
            foreach($datumRange as $key => $value){
                if($key === "date"){
                    $l->datum=$value;
            }
            
            $ph = ranr(23,48)/10;
            $l->kiselost= $ph;

            }
            $limuni[]=$l;
        }
        return $limuni;
    }
}
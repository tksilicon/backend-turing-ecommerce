#!/bin/bash
    #dump.sh
 export PATH="$PATH:dump"
 mysql -uroot -proot tshirtshop < tshirtshop.sql 
    #end of dump.sh
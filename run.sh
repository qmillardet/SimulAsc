#!/bin/bash
ASSERTS=true
RECOMPILE=true
NOTYETIMPLEMENTED=true
LIGNES=true
IO=$(pwd)/IO
cd IO >& /dev/null || exit 1
cd ${IO}/..
ldir=""
c=0
for d in * */* */*/*
do
    cd ${IO}/..
    cd $d >& /dev/null || continue
    cat Main.java >& /dev/null || continue
    cat Evenement.java >& /dev/null || continue
    ldir="${ldir} $(pwd)"
    c=$((c+1))
done
[ $c = 0 ] && exit 1
$ASSERTS && echo - Mesure assert -----------------------------------------------------------------
/bin/rm -f /tmp/asserts
for d in ${ldir}
do
    cd ${IO}/.. ; cd $d
    rm -f excluded output.* *~
    rm -f EssaiLoiDePoisson.java EssaiPressRandomNG.java
    $RECOMPILE && rm -f *.class
    echo $(grep assert *.java | wc -l) asserts ${d} >> /tmp/asserts
done
$ASSERTS && sort -n -r /tmp/asserts
$NOTYETIMPLEMENTED && echo - Mesure notYetImplemented ------------------------------------------------------
c=0
/bin/rm -f /tmp/notYetImplemented
for d in ${ldir}
do
    cd ${IO}/.. ; cd $d
    ls excluded >& /dev/null && continue
    c=$((c+1))    
    echo $(grep notYetImplemented *.java | wc -l) notYetImplemented ${d} >> /tmp/notYetImplemented
done
[ $c = 0 ] && exit 1
$NOTYETIMPLEMENTED && sort -n -r /tmp/notYetImplemented;
$LIGNES && echo - Mesure lignes / fichiers --------------------------------------------------------
c=0
/bin/rm -f /tmp/lignes
for d in ${ldir}
do
    cd ${IO}/.. ; cd $d
    ls excluded >& /dev/null &&	continue
    c=$((c+1))    
    echo -n $(cat *.java | wc -l) "lignes " >> /tmp/lignes
    echo -n $(ls *.java | wc -l) "fichiers " >> /tmp/lignes
    echo " (${d})." >> /tmp/lignes
done
[ $c = 0 ] && exit 1
$LIGNES && sort /tmp/lignes
if $RECOMPILE
then
    echo - Compilation -------------------------------------------------------------------
    c=0
    for d in ${ldir}
    do
	cd ${IO}/.. ; cd $d
	ls excluded >& /dev/null && continue
	c=$((c+1))    
	if javac -encoding UTF-8 *.java >& /dev/null
	then
	    echo ${d}: good
	elif javac -encoding ASCII *.java >& /dev/null
	then
	    echo ${d}: good
	elif javac -encoding ISO-8859 *.java >& /dev/null
	then
	    echo ${d}: good
	else
	    echo ${d}: erreur: \(cd ${b}/${VER} \; javac -encoding UTF-8 \*.java \)
	    touch excluded
	fi	
    done
fi
[ $c = 0 ] && exit 1
for t in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
#for t in 10 15
do
    c=0
    echo - input.${t} / output.${t} ---- assert ON ------------------------------------------------------
    for d in ${ldir}
    do
	cd ${IO}/.. ; cd $d
	ls excluded >& /dev/null && continue
	echo -n ${d}:' '
	if ! java -ea Main < ${IO}/input.${t} >& output.${t}
	then
	    echo tail ${d}/output.${t}
	    touch excluded
	    continue
	fi
	if ! diff ${IO}/output.${t} output.${t} >& /dev/null
	then
	    echo diff ${IO}/output.${t} ${d}/output.${t} 
	    touch excluded
	else
	    echo good
	    c=$((c+1))
	fi
    done
    [ $c = 0 ] && break
done
[ $c = 0 ] && exit 1
for t in 16 17
do
    echo - input.${t} / output.${t} ---- speed assert OFF -----------------------------------------------
    rm -f /tmp/speed.top
    c=0
    for d in ${ldir}
    do
	cd ${IO}/.. ; cd $d
	ls excluded >& /dev/null &&	continue
	echo -n $d ... ' '
	/usr/bin/time --output="speed" --format "%U sec" java Main < ${IO}/input.${t} >& output.${t}
	if ! diff ${IO}/output.${t} output.${t} >& /dev/null
	then
	    echo -n diff ${IO}/output.${t} ${d}/output.${t} " " 
	else
	    c=$((c+1))
	    echo $(cat speed) $d >> /tmp/speed.top
	fi
	cat speed
    done
    echo - sort speed input/output ${t}:
    sort -n /tmp/speed.top
done
echo - FIN ------------------------------------------------------------------------------------

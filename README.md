# Arexx-RA-1-Pro-Controller
Byl vytvořen software pro ovládání robotické ruky Arexx-Ra-1 Pro. Software umožňuje ovládat robot v reálném čase pomocí posuvníků. Software dále umožňuje tyto jednotlivé kroky zaznamenat a uložit do souboru. Soubor pak lze otevřít, procházet krok po kroku. Další funkcionalitou pak je výpočet kinematických úloh robotu.
## Ovládání v reálném čase
Ovládat robota v reálném čase lze pomocí posuvníků. Každý posuvník ovládá jeden motor. Vedle posuvníku pak software zobrazuje hodnotu nastaveného úhlu pro servomotor.
## Ukládání pohybů
Ukládat pohyby lze buďto do nové kolekce pohybů tzv. choreografie pomocí tlačítka "add step to new steps", nebo do načtené choreografie, která je právě prohlížena tlačítkem "add step to actual steps".
## Načítání pohybů
Načítat pohyby pak lze pomocí tlačítka "Open steps". Následně se zobrazí okno, které vyzve k zadání názvu souboru, který má program načíst.
## Přímá kinematická úloha
Přímá kinematická úloha je zde realizována prostřednictvím několika rovnic. Výpočet pak řeší třída "ForwardKinematics" prostřednictvím funkce "calculateFK". Funkce obdrží paremetrem pole úhlů, pro které vypočítá souřadnice endEfektoru.

Rovnice prostřednictvím kterých výpočet řeší, byly získány sestavením maticových předpisů pro jednotlivé klouby a ramena robotu. Tyto maticové předpisy byly následně dány do maticové rovnice v pořadí od základny po endEfektor.

Výsledná matice pak obsahovala předpisy funkcí pro výpočet souřadnic a orientace endEfektoru.

Dále byly řešeny pouze souřadnice endEfektoru. Funkční předpisy pro výpočet souřadnic tedy byly vyjmuty a následně dále upraveny pro zjednodušení výpočtu. Takto upravené rovnice pakjsou použity pro výpočet přímé kinematické úlohy. Tímto zjednodušením rovnic byla snížena výpočetní náročnost.
## Inverzní kinematická úloha
Inverzní kinematická úloha je zde řešena prvotním vypočtením souřadnic pro jednotlivé úhlové nastavení v rozmení 5° pomocí přímé kinematiky. Následně veškeré tyto možnosti jsou uloženy do souboru (není nutnost vypočítávat pořád dokola). Pro nalezení úhlového nastevení robotu je tedy tento soubor prohledán, program nalezne nejbližší možný bod. Tento bod následně slouží spíše jako orientační, protože program následně pomocí přímé kinematiky vytvoří nový podrobnější seznam (tentokrát v rozmezí 1°). Tento nově vytvořený seznam pak prohledá a najde přesnější nastavení úhlů pro jednotlivé klouby, které předá grafickému rozhraní.
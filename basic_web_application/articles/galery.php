<?php
	include '../php/selectStyleArticles.php';
?>

<!DOCTYPE html>
<html lang="cs">
    <head>
        <meta charset="utf-8">
		<meta name="viewport" content="device-width, initial-scale=1.0">
        <?php
			echo ($selectedSkin);
		?>
		<link type="text/css" rel="stylesheet" href="../css/style-print.css" media="print">
		<link rel="stylesheet" href="../css/lightbox.css" type="text/css" />
		<script src="../js/jquery-1.7.2.min.js"></script>
		<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../js/jquery.smooth-scroll.min.js"></script>
		<script src="../js/lightbox.js"></script>
        <title>Salón-výšivky</title>
    </head>

    <body>
        <header>
            <nav class="mainNav">
                <ul>
					<li><img class="horizontal-align" src="../images/logo.png" alt="logo salónu"></li>
                    <li><a class="horizontal-align" href="../index.php">O nás</a></li>
                    <li><a class="horizontal-align" href="services.php">Služby a ceník</a></li>
                    <li><a class="active horizontal-align" href="galery.php">Galerie</a></li>
                    <li><a class="horizontal-align" href="contact.php">Kontakt</a></li>
                    <li><a class="horizontal-align" href="reference.php">Reference</a></li>
                </ul>
            </nav>
			<nav class="galNav">
				<ul>
					<li><a class="horizontal-align" href="#bags">Vaky a tašky</a></li>
					<li><a class="horizontal-align" href="#pillows">Polšťářky</a></li>
					<li><a class="horizontal-align" href="#teddies">Plyšáci</a></li>
					<li><a class="horizontal-align" href="#folklor">Folklor</a></li>
					<li><a class="horizontal-align" href="#christmas">Vánoční ozdoby</a></li>
				</ul>
			</nav>
        </header>

        <main class="topic">
			<div id="galery">
				<div>
					<h2 id="bags">Vaky a tašky</h2>
					<a href="../images/galery/img(1).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(1).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(2).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(2).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(3).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(3).jpg" alt="Vak z použitých jeanů" /></a>				
					<a href="../images/galery/img(4).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(4).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(5).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(5).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(33).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(33).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(24).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(24).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(30).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(30).jpg" alt="Vaky s vyšitými jmény" /></a>
					<a href="../images/galery/img(31).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(31).jpg" alt="Dětské vaky se zajěčíma ušima" /></a>
					<a href="../images/galery/img(14).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(14).jpg" alt="Taška s vyšitou včelou" /></a>
					<a href="../images/galery/img(25).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(25).jpg" alt="Plátěná taška s výšivkou" /></a>
					<a href="../images/galery/img(26).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(26).jpg" alt="Plátěná taška s výšivkou" /></a>
					<a href="../images/galery/img(27).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(27).jpg" alt="Plátěná taška s výšivkou" /></a>
					<a href="../images/galery/img(28).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(28).jpg" alt="Plátěná taška s výšivkou" /></a>
					<a href="../images/galery/img(29).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(29).jpg" alt="Plátěná taška s výšivkou" /></a>			
					<a href="../images/galery/img(32).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(32).jpg" alt="Plátěná taška s výšivkou" /></a>
					<a href="../images/galery/img(20).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(20).jpg" alt="Taška there is no planet B" /></a>
					<a href="../images/galery/img(19).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(19).jpg" alt="Taška there is no planet B" /></a>

					<h2 id="pillows">Polštářky</h2>
					<a href="../images/galery/img(6).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(6).jpg" alt="Ručníky s výšivkou" /></a>	
					<a href="../images/galery/img(7).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(7).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(8).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(8).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(9).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(9).jpg" alt="Ručníky s výšivkou" /></a>	
					<a href="../images/galery/img(10).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(10).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(11).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(11).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(12).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(12).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(13).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(13).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(15).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(15).jpg" alt="Ručníky s výšivkou" /></a>									
					<a href="../images/galery/img(16).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(16).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(34).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(34).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(35).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(35).jpg" alt="Vak z použitých jeanů" /></a>
					
					<h2 id="teddies">Plyšáci</h2>
					<a href="../images/galery/img(23).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(23).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(18).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(18).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(17).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(17).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(21).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(21).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(22).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(22).jpg" alt="Ručníky s výšivkou" /></a>
					<a href="../images/galery/img(23).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(23).jpg" alt="Vak z použitých jeanů" /></a>
					
					<h2 id="folklor">Folklor</h2>
					<a href="../images/galery/img(37).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(37).jpg" alt="Vak z použitých jeanů" /></a>									
					<a href="../images/galery/img(38).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(38).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(40).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(40).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(39).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(39).jpg" alt="Vak z použitých jeanů" /></a>				
					<a href="../images/galery/img(41).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(41).jpg" alt="Vak z použitých jeanů" /></a>
					

					<h2 id="christmas">Vánoční ozdoby</h2>
					<a href="../images/galery/img(36).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(36).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(48).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(48).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(42).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(42).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(43).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(43).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(44).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(44).jpg" alt="Vak z použitých jeanů" /></a>	
					<a href="../images/galery/img(45).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(45).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(46).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(46).jpg" alt="Vak z použitých jeanů" /></a>
					<a href="../images/galery/img(47).jpg" title="Ručníky s výšivkou" rel="lightbox[galery]"><img src="../images/galery/icon(47).jpg" alt="Vak z použitých jeanů" /></a>
				</div>
			</div>
		</main>

        <aside>
            <h2>Navštivte také</h2>
            <a href="http://www.okstavby.cz/" target="_blank"><img src="../images/okstavby.png" alt="logo společnosti ok stavby"></a>
            <a href="https://www.facebook.com/darekjakovysity/" target="_blank"><img src="../images/darekjakovysity.png" alt="logo společnosti dárek jako vyšitý"></a>
            <a href="https://www.penzion-berany.cz/" target="_blank"><img src="../images/berany.jpg" alt="náhledový obrazek nezionu Berany"></a>
			<a href="https://praguecycleboat.com/" target="_blank"><img src="../images/cycleboat.jpg" alt="logo společnosti Prague cycle boat"></a>
			<a href="http://okdlazby.cz/" target="_blank"><img src="../images/okdlazby.png" alt="logo společnosti OK Dlažby"></a>
		</aside>

        <footer>
            <nav class="footNav">
                <ul>
                    <li><a href="../index.php">O nás</a></li>
                    <li><a href="services.php">Služby a ceník</a></li>
                    <li><a href="galery.php">Galerie</a></li>
                    <li><a href="contact.php">Kontakt</a></li>
                    <li><a href="reference.php">Reference</a></li>
                </ul>
				<a href="https://www.instagram.com/darek_jako_vysity/?hl=cs"><img class="social" src="../images/ig.png" alt="instagram logo"></a>
				<a href="https://www.facebook.com/darekjakovysity/"><img class="social" src="../images/fb.png" alt="facebook logo"></a>	
            </nav>		
		</footer>
    </body>
</html>
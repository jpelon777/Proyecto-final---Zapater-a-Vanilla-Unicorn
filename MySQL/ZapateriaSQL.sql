-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: zapateria
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `categoria` varchar(50) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Nike Zoom Fly',12,1399,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\ZOOM.png'),(2,'Adidas Solar Glide',9,1349,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\Solar.jpg'),(3,'Puma RS-X',14,1199,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\Pumar rsx.jpg'),(4,'Reebok Classic',10,1099,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\Reebok classic.jpeg'),(5,'Under Armour Slingride',11,1249,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\Under armour.jpg'),(6,'Asics Kayano',8,1399,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\asics.jpeg'),(7,'New Balance Fresh Foam',13,1299,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\Fresh.jpg'),(8,'Mizuno Wave Inspire',7,1199,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\mizuno.jpg'),(9,'Skechers D\'Lites',15,1099,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\dlites.jpg'),(10,'Fila Disruptor',12,999,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\fila.jpg'),(11,'Sandalia Playa Hombre',20,299,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\hombre playa.jpeg'),(12,'Sandalia Playa Mujer',18,349,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\playa mujer.jpg'),(13,'Sandalia Casual Dama',25,399,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\casual dama.jpg'),(14,'Sandalia Casual Caballero',22,429,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\casual caballero.jpeg'),(15,'Sandalia Deportiva ',15,499,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\deportiva.png'),(16,'Sandalia Elegante Mujer',17,559,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\elegante.jpeg'),(17,'Sandalia Plataforma',12,629,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\plataforma.jpg'),(18,'Sandalia Hombre',20,399,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\comoda hombre.jpeg'),(19,'Sandalia Antideslizante',19,479,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\antiderapante.jpg'),(20,'Sandalia Moda Verano',21,509,'SANDALIAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Sandalias\\verano.jpeg'),(21,'Zapato Ejecutivo Negro',16,849,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\negro.jpeg'),(22,'Zapato Ejecutivo Café',15,829,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\cafe.jpg'),(23,'Zapato Escolar Niña',20,699,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\niña.jpg'),(24,'Zapato Escolar Niño',18,679,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\niño.jpeg'),(25,'Zapato Vestir Hombre',22,999,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\vestir.jpeg'),(26,'Zapato Vestir Mujer',20,1049,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\mujer1.jpeg'),(27,'Zapato Casual Hombre',25,799,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\casualh.jpeg'),(28,'Zapato Casual Mujer',24,799,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\casuual mu.jpg'),(29,'Zapato Formal Hombre',19,899,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\formalH.jpg'),(30,'Zapato Formal Mujer',17,899,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos\\formalM.jpeg'),(31,'Zapatilla Casual Blanca',15,1099,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\blanca.jpeg'),(32,'Zapatilla Negra',14,1149,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\negras.jpeg'),(33,'Zapatilla Plataforma Rosa',13,1249,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\rosa.png'),(34,'Zapatilla Azul',12,1199,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\azul.jpeg'),(35,'Zapatilla Verde',11,1099,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\verde.jpeg'),(36,'Zapatilla Roja',15,1149,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\roja.jpeg'),(37,'Zapatilla Marrón',10,1199,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\marron.jpeg'),(38,'Zapatilla Ballet',14,1099,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\ballet.jpg'),(39,'Zapatilla Plateadas',16,999,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\plateadas.jpeg'),(40,'Zapatilla Price shoes',13,1099,'ZAPATILLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatillas\\price.jpeg'),(41,'Pantufla Invierno Azul',18,349,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\invierno azul.jpeg'),(42,'Pantufla Invierno Rosa',20,359,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\invierno rosa.jpg'),(43,'Pantufla Piel Marrón',15,399,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\piel marron.jpeg'),(44,'Pantufla Piel Gris',22,429,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\piel gris.jpg'),(45,'Pantufla Animal ',22,429,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\animal.jpeg'),(46,'Pantufla Blanca',24,359,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\comoda blanca.jpg'),(47,'Pantufla Antideslizante',19,399,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\antideslizante.jpg'),(48,'Pantufla Tejida ',17,419,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\lana.jpeg'),(49,'Pantufla De Casa Gris',20,389,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\casa gris.jpg'),(50,'Pantufla De Casa Rosa',21,369,'PANTUFLAS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Pantuflas\\casa rosa.jpeg'),(51,'Nike Air Zoom',15,1250,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\air zoom.jpg'),(52,'Adidas Ultraboost',12,1350,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\ultraboost.jpg'),(53,'Puma Running XT',10,1150,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\puma runnig.jpg'),(54,'Reebok Nano',8,1400,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\nano.jpg'),(55,'Hovr',10,1500,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\hovr.jpg'),(56,'Asics Gel-Kayano',11,1300,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\gel kayano.jpg'),(57,'New Balance 550 ',9,1200,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\550.jpeg'),(58,'Mizuno Wave Rider',13,1250,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\wave rider.jpg'),(59,'Saucony Kinvara',7,1150,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\saucony.jpg'),(60,'Skechers GoRun',10,1100,'CALZADO DEPORTIVO','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Calzado Deportivo\\go run.jpg'),(61,'Sandalia Crocs Clásica',20,520,'SANDALIAS','crocs.jpg'),(62,'Sandalia Reef',18,530,'SANDALIAS','reef.jpg'),(63,'Sandalia Birkenstock',12,600,'SANDALIAS','birkenstock.jpg'),(64,'Sandalia Havaianas',25,610,'SANDALIAS','havaianas.jpg'),(65,'Sandalia Teva Original',14,450,'SANDALIAS','teva.jpg'),(66,'Sandalia Columbia',10,440,'SANDALIAS','columbia.jpg'),(67,'Sandalia Timberland',9,520,'SANDALIAS','timberland.jpg'),(68,'Sandalia Merrell',11,530,'SANDALIAS','merrell.jpg'),(69,'Sandalia KEEN Newport',13,580,'SANDALIAS','keen.jpg'),(70,'Sandalia Adidas Adilette',17,590,'SANDALIAS','adilette.jpg'),(71,'Zapato Oxford ',10,880,'ZAPATOS','oxford.jpg'),(72,'Zapato Derby Café',8,870,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos'),(73,'Zapato Loafer Elegante',6,750,'ZAPATOS','loafer.jpg'),(74,'Zapato Monk Strap',7,740,'ZAPATOS','monk.jpg'),(75,'Zapato Clarks',9,700,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos'),(76,'Zapato Brogue ',11,710,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos'),(77,'Zapato Vaqueros',5,800,'ZAPATOS','vestir.jpg'),(78,'Zapato de Cuero ',13,820,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos'),(79,'Zapato Ferrato',14,900,'ZAPATOS','C:\\Users\\PBA GOAT\\Desktop\\ProyectoLoginZapateria\\ImagenesProductos\\Zapatos'),(80,'Zapato Timberland',12,920,'ZAPATOS','escolar.jpg'),(81,'Zapatilla Converse Chuck',15,1100,'ZAPATILLAS','converse.jpg'),(82,'Zapatilla Vans Old Skool',14,1100,'ZAPATILLAS','vans.jpg'),(83,'Zapatilla Fila Disruptor',10,1150,'ZAPATILLAS','fila.jpg'),(84,'Zapatilla Skechers D´Lites',13,1250,'ZAPATILLAS','dlites.jpg'),(85,'Zapatilla Puma Suede',8,1200,'ZAPATILLAS','suede.jpg'),(86,'Zapatilla Nike Court',12,1100,'ZAPATILLAS','court.jpg'),(87,'Zapatilla Adidas Nizza',7,1150,'ZAPATILLAS','nizza.jpg'),(88,'Zapatilla DC Shoes',9,1200,'ZAPATILLAS','dc.jpg'),(89,'Zapatilla Le Coq Sportif',6,1100,'ZAPATILLAS','lecoq.jpg'),(90,'Zapatilla New Balance 574',10,1000,'ZAPATILLAS','nb_574.jpg'),(91,'Pantufla Polar Clásica',20,350,'PANTUFLAS','polar.jpg'),(92,'Pantufla Peluche Oso',18,360,'PANTUFLAS','oso.jpg'),(93,'Pantufla Memory Foam',15,400,'PANTUFLAS','memory.jpg'),(94,'Pantufla Cerrada Invierno',12,390,'PANTUFLAS','invierno.jpg'),(95,'Pantufla Abierta Mujer',9,430,'PANTUFLAS','abierta.jpg'),(96,'Pantufla con Antideslizante',14,360,'PANTUFLAS','antideslizante.jpg'),(97,'Pantufla Estampada Niños',17,400,'PANTUFLAS','ninos.jpg'),(98,'Pantufla Elegante Hombre',11,420,'PANTUFLAS','elegante.jpg'),(99,'Pantufla Lana Natural',10,390,'PANTUFLAS','lana.jpg'),(100,'Pantufla Divertida Emoji',13,370,'PANTUFLAS','emoji.jpg');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contraseña` varchar(100) NOT NULL,
  `Rol` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Omar','Ramirez','cositas3924@gmail.com','340b66c1f18818adbad550bed6b3f05d91250328c4cbb856e26cd20644d7caec','Cajero');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-08 23:41:19

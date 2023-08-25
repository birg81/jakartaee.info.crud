[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

<a href="#IT">🇮🇹</a> - <a href="#EN">🇬🇧</a>

# Web App con Jakarta EE, Tomcat e Maven 🚀

<a name="IT"></a>

Questo repository presenta una semplice guida su come creare una web app utilizzando la tecnologia Jakarta Enterprise Edition (EE) e il server Tomcat, tutto attraverso l'IDE [Visual Studio Code](https://code.visualstudio.com/). Se sei abituato ad utilizzare come IDE [Eclipse](https://www.eclipse.org/), questa guida ti mostrerà un modo diverso ed efficiente per sviluppare le tue applicazioni.

## Cosa fa il progetto?

Il progetto presenta interessanti features, come:

* informazioni sulle URL Query String
* CRUD completo REST con endpoint webapi (il comportamento simula SpringBoot)
* creazione di una WebApp, nello specifico una lista di persone con possibilità di inserimento, modifica ed eliminazione
* realizzazione di un back end e di un front end senza nessun utilizzo di framework a meno di CSS di [Bootstrap](https://getbootstrap.com) native

## Backend con Jakarta EE e Maven

Abbiamo creato servlets per rispondere alle richieste:

* Creata una servlet che restituisce informazioni in formato JSON relative alle interrogazioni e alle URL query string.
* Implementata una servlet per simulare un CRUD completo.
* Risolto problemi di estrazione dei dati dalle richieste utilizzando Stream API.

Sebbene il progetto abbia endpoint funzionanti, consigliamo l'uso di [Spring Boot](https://spring.io/projects/spring-boot) per un CRUD completo in ambito produzione.

## Frontend with HTML, CSS, and Vanilla JavaScript

Abbiamo sviluppato un front end senza framework:

1. Realizzato un'interfaccia utente per gestire una lista di contatti.
2. Utilizzato HTML, CSS e JavaScript Vanilla, con un tocco di [Bootstrap](https://getbootstrap.com/).

## Initial Setup

Prima di tutto, bisogna configura configurato l'ambiente di sviluppo, installando i dovuti plugin di [Visual Studio Code](https://code.visualstudio.com/), nello specifico:

* Aggiungi l'estensione [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven).
* Aggiungi l'estensione [Community Server Connector](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-community-server-connector)

Successivamente scarica:

* [Maven](https://maven.apache.org/download.cgi)
* l'ultima verione di [Tomcat](http://tomcat.apache.org/)

Adesso:

* configura i dettagli necessari nel file `tomcat-users.xml`; a titolo di esempio riporto la mia configurazione

```xml
<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users
	version="1.0"
	xmlns="http://tomcat.apache.org/xml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
>
	<role rolename="admin"/>
	<role rolename="admin-script"/>
	<role rolename="admin-gui"/>
	<role rolename="manager"/>
	<role rolename="manager-status"/>
	<role rolename="manager-script"/>
	<role rolename="manager-jmx"/>
	<role rolename="manager-gui"/>
	<user username="admin" password="admin" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
	<user username="root" password="root" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
	<user username="user" password="1234" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
	<user username="sa" password="sa" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
</tomcat-users>
```

* configura **Maven for Java** indicando il percorso della cartella di Maven
* configura **Community Server Connector** creando un nuovo server indicando che già hai scaricato tomcat e settando la cartella di installazione

## Creazione del Progetto

* Apri Visual Studio Code e premi `CTRL + MAIUSC + P`.
* Scrivi "Maven: Create Maven Project".
* Seleziona come Archetype `maven-archetype-webapp`.
* successivamente verranno chiesti `<groupId>`, ovvero, il package principale dove solo le classi e le servlet e l'`<artifactId>` ovvero il nome del progetto ed altre informazioni.

## Sistemiamo il file del progetto

A posteriori, dopo aver scaricato tutte le dipendenze, *vscode* si riavvierà presentando il progetto in forma molto minimale: sarà uno *starter kit*, che presenterà le seguenti cartelle e file:

```bash
/<artifactId>
-/src
--/main
---/webapp
----/WEB-INF
-----web.xml
----index.jsp
-pom.xml
```

In primo luogo va modificato:

* il file [`web.xml`](./src/main/webapp/WEB-INFO/web.xml), si consiglia di copiare di questo presentato nel repository.
* modificato il file [`pom.xml`](./pom.xml)

nello specifico, va sistemato il seguente spezzone:

```xml
<maven.compiler.source>1.7</maven.compiler.source>
<maven.compiler.target>1.7</maven.compiler.target>
```

sostituendo la versione di Java da 1.7 con 20 (attualmente l'ultima versione)

* inoltre va inserito nella sessione `xml <dependencies>` la dipendenza di [**Tomcat Catalina**](https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-catalina)

```xml
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-servlet-api</artifactId>
	<version>10.1.12</version>
</dependency>
```

Questa configurazione non prevede l'uso di servlet, quindi manualmente bisogna aggiungere le cartelle e i file necessari come nell'esempio

```bash
/<artifactId>
-/src
--/main
---/java
----/<groupId>
-----<Servlet>.java
---/webapp
----/WEB-INF
-----web.xml
----index.jsp
-pom.xml
```

Si rammenta che la scrittura delle servlet non prevede aiuto o supporto da parte dell'IDE, quindi il programmatore dovrà ricordarsi buona parte del codice.
Risulta quindi molto importante questo progetto che rappresenta una sorta di template di riferimento.

## Testing e Deploy
Per testare l'applicazione serve creare il pacchetto [war](https://it.wikipedia.org/wiki/WAR_(formato_di_file)) `<artifactId>.war`, per far ciò basta:

* cliccare sulla barra **Maven**
* selezionare l'`<artifactId>`
* selezionare la voce `Run Maven Commands…`
* cliccare su `package`

questo produrrà:

* la compilazione del progetto, spunterà dunque una cartella `/target`
* all'interno di questa cartella ci sarà il nostro file war

Quindi selezionando sulla barra `SERVERS > Community Server Connector > Tomcat`, selezionare `Start Server`, in modo da avviare il server.

Avviato il server non rimane altro che fare il *deploy* del file war sul server; basterà selezionarlo e fare `Run on Server`, selezionare il server creato.

Per testare l'applicazione

* aprire il proprio browser e recarsi all'indirizzo [`localhost:8080/<artifactId>`](http://localhost:8080/jakartaeeinfocrud).

### IMPORTANTE

* Ogni modifica del codice richiede la creazione di un nuovo file WAR per l'aggiornamento su Tomcat.

## Bug noti

* Per la persistenza dei dati non ho usato nessun DB ma un semplice `ArrayList<Person>` per non rendere il codice ulteriormente complicato, ciò comporta che ogni qualvolta si riavvia il progetto, i dati inseriti vengono azzerati
* il back-end presenta un problema con la modifica, infatti vengono modificati tutti i campi tranne il campo `age`.
* il front-end, essendo riciclato da un progetto Spring con ORM, presenta maggiormente dei problemi che credo dipendano dal file [restscript.js](./src/main/webapp/script/restscript.js); quindi le funzioni di READ e CREATE vengono svolte correttamente, ma ho gravi problemi con quelle di **DELETE** e **UPDATE**.

## Agility Note

Anche se questa approccio potrebbe sembrare meno immediato rispetto ad Eclipse, una volta abituati, i vantaggi sono notevoli e garantiti dall'enorme versatilità di Maven.

## License

Questo progetto è distribuito sotto la licenza [MIT](LICENSE).


<hr/><a name="EN"></a>

<a href="#IT">🇮🇹</a> - <a href="#EN">🇬🇧</a>

# Web App with Jakarta EE, Tomcat, and Maven 🚀

This repository provides a simple guide on creating a web app using Jakarta Enterprise Edition (EE) technology and the Tomcat server, all within the [Visual Studio Code](https://code.visualstudio.com/) IDE. If you're accustomed to using [Eclipse](https://www.eclipse.org/) as your IDE, this guide will introduce you to a different and efficient way to develop your applications.

## What Does the Project Do?

The project introduces interesting features, including:

* Information about URL Query Strings
* Full CRUD operations with REST endpoints (simulating Spring Boot behavior)
* Creation of a web app, specifically a contact list with capabilities for insertion, modification, and deletion
* Backend and frontend development without any framework except for native [Bootstrap](https://getbootstrap.com) CSS

## Backend with Jakarta EE and Maven

We've created servlets to respond to requests:

* Designed a servlet that provides JSON information regarding queries and URL query strings.
* Implemented a servlet to simulate a complete CRUD operation.
* Addressed data extraction issues from requests using Stream API.

While the project has functional endpoints, we recommend using [Spring Boot](https://spring.io/projects/spring-boot) for a complete production-ready CRUD solution.

## Frontend with HTML, CSS, and Vanilla JavaScript

We developed a framework-free frontend:

1. Created a user interface to manage a list of contacts.
2. Utilized HTML, CSS, and Vanilla JavaScript, with a touch of [Bootstrap](https://getbootstrap.com/).

## Initial Setup

First and foremost, configure your development environment by installing the required plugins for [Visual Studio Code](https://code.visualstudio.com/), specifically:

* Add the extension [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven).
* Add the extension [Community Server Connector](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-community-server-connector).

Next, download:

* [Maven](https://maven.apache.org/download.cgi)
* The latest version of [Tomcat](http://tomcat.apache.org/)

Now:

* Configure the necessary details in the `tomcat-users.xml` file. As an example, I'm providing a configuration snippet below:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users
	version="1.0"
	xmlns="http://tomcat.apache.org/xml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
>
	<role rolename="admin"/>
	<role rolename="admin-script"/>
	<role rolename="admin-gui"/>
	<role rolename="manager"/>
	<role rolename="manager-status"/>
	<role rolename="manager-script"/>
	<role rolename="manager-jmx"/>
	<role rolename="manager-gui"/>
	<user username="admin" password="admin" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
	<user username="root" password="root" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
	<user username="user" password="1234" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
	<user username="sa" password="sa" roles="admin,admin-script,admin-gui,manager,manager-status,manager-script,manager-jmx,manager-gui"/>
</tomcat-users>
```

* Configure **Maven for Java** by indicating the path to the Maven folder.
* Configure **Community Server Connector** by creating a new server instance and specifying the Tomcat installation directory.

## Project Creation

* Open Visual Studio Code and press `CTRL + SHIFT + P`.
* Type "Maven: Create Maven Project".
* Select the `maven-archetype-webapp` archetype.
* You'll be prompted for the `<groupId>` (main package where your classes and servlets reside) and `<artifactId>` (project name) among other details.

## Adjusting the Project File

After downloading all the dependencies, *vscode* will present you with a minimal project structure, which serves as a starter kit. It includes the following folders and files:

```bash
/<artifactId>
-/src
--/main
---/webapp
----/WEB-INF
-----web.xml
----index.jsp
-pom.xml
```

First, modify:

* The [`web.xml`](./src/main/webapp/WEB-INF/web.xml) file. I recommend copying the example presented in this repository.
* The [`pom.xml`](./pom.xml) file.

Specifically, adjust the following snippet:

```xml
<maven.compiler.source>1.7</maven.compiler.source>
<maven.compiler.target>1.7</maven.compiler.target>
```

Replace the Java version from 1.7 with 20 (the latest version currently).

Additionally, include the **Tomcat Catalina** dependency in the `<dependencies>` section of the `pom.xml` file:

```xml
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-servlet-api</artifactId>
	<version>10.1.12</version>
</dependency>
```

This configuration doesn't involve using servlets, so you need to manually add the necessary folders and files as shown in the example:

```bash
/<artifactId>
-/src
--/main
---/java
----/<groupId>
-----<Servlet>.java
---/webapp
----/WEB-INF
-----web.xml
----index.jsp
-pom.xml
```

It's important to note that writing servlets doesn't receive the same level of support or assistance from the IDE as other languages. As such, developers need to be familiar with the coding process. Thus, this project serves as a valuable reference template.

## Testing and Deployment

To test the application, you need to create a [WAR file](https://en.wikipedia.org/wiki/WAR_(file_format)) named `<artifactId>.war`. To achieve this:

* Click on the **Maven** bar.
* Select `<artifactId>`.
* Choose `Run Maven Commands...`.
* Click on `package`.

This will result in:

* Project compilation, creating the `/target` folder.
* The WAR file will be located within this folder.

Next, select `SERVERS > Community Server Connector > Tomcat`, then click on `Start Server` to initiate the server.

Once the server is running, deploy the WAR file by selecting it and choosing `Run on Server`. Then, select the created server.

To test the application:

* Open your browser and navigate to [`localhost:8080/<artifactId>`](http://localhost:8080/jakartaeeinfocrud).

### IMPORTANT

* Every code modification requires creating a new WAR file to update Tomcat.

## Known Bugs

* For data persistence, I haven't utilized a database, opting for a simple `ArrayList<Person>` to keep the code less complicated. As a consequence, each time the project restarts, the entered data gets reset.
* The backend experiences an issue with modification, as it changes all fields except for the `age` field.
* The frontend, being recycled from a Spring project with ORM, has more problems which I believe stem from the [restscript.js](./src/main/webapp/script/restscript.js) file. Consequently, the READ and CREATE functions operate correctly, but I encounter significant issues with the DELETE and UPDATE operations.

## Agility Note

Although this approach might seem less straightforward initially compared to Eclipse, once accustomed, the benefits are substantial and guaranteed by Maven's immense versatility.

## License

This project is distributed under the [MIT License](LICENSE).

## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/biagio-rosario-greco-77145774/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/birg_81)

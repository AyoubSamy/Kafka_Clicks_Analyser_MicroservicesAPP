# Real-Time Click Analytics Pipeline

![Big Data](https://img.shields.io/badge/Big_Data-Streaming-blue)
![Kafka](https://img.shields.io/badge/Apache_Kafka-4.1.0-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)

## 🛠 Technologies & Tools

### Big Data & Streaming
![Kafka](https://img.shields.io/badge/Apache_Kafka-Stream_Processing-black?style=for-the-badge&logo=apachekafka)
![Kafka Streams](https://img.shields.io/badge/Kafka_Streams-Real--time_Analytics-orange?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Infrastructure-blue?style=for-the-badge&logo=docker)

### Backend & Frontend
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-Backend_API-brightgreen?style=for-the-badge&logo=springboot)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-UI_Templates-005F0F?style=for-the-badge)
![JavaScript](https://img.shields.io/badge/JavaScript-Fetch_API-yellow?style=for-the-badge&logo=javascript)

---

## -  Objective
L'objectif de cette application est de concevoir un pipeline **End-to-End** capable de capturer, traiter et visualiser des événements utilisateur (clics) en temps réel avec une latence minimale. Le projet démontre comment transformer un flux de données brut en insights exploitables via une architecture orientée événements (EDA).

## -  Architecture
Le projet est découpé en trois micro-services distincts orchestrés autour d'un cluster Kafka :

1.  **Producer (Port 8080)** : Une interface web Spring Boot qui capture les clics et les publie dans le topic `click`.
2.  **Kafka Streams Engine** : Une application de traitement qui consomme les clics, effectue un comptage étatique (*Stateful*) par utilisateur et produit les résultats dans `click-counts`.
3.  **Consumer & Dashboard (Port 8082)** : Un service qui consomme les agrégats, les expose via une API REST et les affiche sur un dashboard dynamique.

## -  What I Learned
Ce projet m'a permis de maîtriser des concepts avancés du Big Data et du développement logiciel :

* **Kafka Streams Topology** : Conception de topologies incluant `selectKey`, `groupByKey` et `count()`.
* **Stateful Processing** : Gestion des *State Stores* (RocksDB) pour maintenir des compteurs persistants sans base de données externe.
* **Serialization (SerDes)** : Résolution de conflits de formats binaires vs textuels entre différents micro-services.
* **Real-time UI** : Synchronisation du backend et du frontend via l'API Fetch pour des mises à jour sans rechargement de page.
* **Infrastructure as Code** : Déploiement et gestion d'un cluster Kafka multi-services via Docker Compose.

---


## - How to Run

1. **Lancer Kafka** :
   ```bash
   docker-compose up -d
   ```
2. **Démarrer les services** :
    - Lancer l'application `ProducerApplication`.
    - Lancer l'application `StreamProcessingApp`.
    - Lancer l'application `ConsumerApplication`.
3. **Accéder aux interfaces** :
    - Producer : `http://localhost:8080`
    - Dashboard : `http://localhost:8082`

---
© 2026 - Ayoub SAMY - ENSET Mohammedia
```


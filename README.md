# Smart Chatbot Platform

![Chatbot Banner](https://technoloqie.cloud/img/logo1.png)

## 🚀 Vision and Purpose
The **Smart Chatbot Platform** is an enterprise-grade solution designed to revolutionize digital marketing and customer service. By leveraging Artificial Intelligence, Machine Learning (Transformers/NLP), and a robust microservices architecture, the platform enables companies to provide 24/7 automated, natural, and secure interactions.

This project was developed to bridge the gap between complex AI models and real-world business needs, providing a scalable ecosystem to simulate human-like conversations and manage knowledge extraction through RAG (Retrieval-Augmented Generation).

---

## 🛠 Architecture Overview
The system is built on a modular microservices architecture, ensuring high availability, scalability, and security.

### Backend (Java / Spring Boot 2.7.11)
*   **`tec-chatbot-ws`**: The central orchestrator. Manages REST and WebSocket (STOMP) communication, routing user messages to the AI engine.
*   **`tec-document-loader-ws`**: Handles the RAG pipeline. Responsible for reading, splitting, and embedding documents (PDF, TXT, DOC) into the vector database.
*   **`tec-framework-security`**: A dedicated security layer managing JWT authentication, authorization, and multi-role access (Admin, User, Client).

### AI & Data Science (Python)
*   **`tec-data-explorer-api`**: The intelligence core. Powered by **TensorFlow**, **Keras**, and **Hugging Face Transformers**. It handles NLP tasks, intent classification, and similarity searches (ANN) using vector embeddings.

### Frontend (React)
*   **`tec-chat-app`**: A modern, responsive micro-frontend that provides a real-time chat interface with interactive feedback and visual message statuses.

---

## ✨ Key Features (Based on User Histories)
*   **HU1: Automated Consultation**: Seamless access via web-bot for immediate, coherent responses to business-specific queries.
*   **HU2: Flow Management**: Administrative interface to upload CSV/Database sources to dynamically update the chatbot's knowledge base.
*   **HU3: AI Contextual Awareness**: Advanced intent classification using Deep Learning to identify potential leads and provide natural interactions.
*   **HU4: Dynamic Analytics**: Real-time KPI dashboard to monitor response effectiveness, resolution rates, and user satisfaction.
*   **HU5: RBAC Security**: Robust role-based access control ensuring data integrity and administrative security via `tec-framework-security`.

---

## 📸 Screenshots & Diagrams

### 🗺️ System Architecture

<div class="mxgraph" style="max-width:100%;border:1px solid transparent;" data-mxgraph="{&quot;highlight&quot;:&quot;#0000ff&quot;,&quot;lightbox&quot;:false,&quot;nav&quot;:true,&quot;resize&quot;:true,&quot;page&quot;:3,&quot;dark-mode&quot;:&quot;light&quot;,&quot;toolbar&quot;:&quot;zoom layers tags&quot;,&quot;edit&quot;:&quot;_blank&quot;,&quot;url&quot;:&quot;https://drive.google.com/uc?id=11qEmWJ0ir9RmQVn_2lMrIeYkJvzS8wEo&amp;export=download&quot;}"></div>

*Refer to diagrams for full DFD and Sequence diagrams.*
[diagrams](https://dijavaji.github.io/tec-chatbot-paper/)

### 💬 Chat Interface
![Frontend Screenshot](https://via.placeholder.com/800x400?text=tec-chat-app+Interface+Preview)

### 📊 Admin Dashboard
![Analytics Preview](https://via.placeholder.com/800x400?text=KPI+Analytics+Dashboard)

---

## ⚙️ Configuration & Setup

### Prerequisites
*   **Java 17** & **Maven 3.8+**
*   **Python 3.9+** (TensorFlow, PyTorch, Transformers)
*   **Docker** & **Docker Compose**
*   **Databases**: MySQL 8.0 (Relational) & MongoDB/VectorDB (Embeddings)

### Environment Variables
Configure the following placeholders in your `.env` or application properties:

```env
# Security
JWT_SECRET=[YOUR_SECRET_KEY]
SECURITY_ENABLED=true

# AI Service
TEC_CHATBOT_API_URL=http://tec-data-explorer-api:5000
OPENAI_API_KEY=[IF_USING_EXTERNAL_LLM]

# Telegram Integration
TELEGRAM_TOKEN=[YOUR_BOT_TOKEN]
TELEGRAM_USER=[YOUR_BOT_USERNAME]

# Databases
MYSQL_URL=jdbc:mysql://localhost:3306/tec_message_db
MONGODB_URI=mongodb://localhost:27017/vector_db
```

### Installation
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-repo/tec-message-ws.git
    ```
2.  **Run with Docker Compose**:
    ```bash
    docker-compose up --build
    ```

---

## 📚 Technical Documentation
For a deep dive into the system's design, refer to the following local documents:
*   [System Diagrams](https://dijavaji.github.io/tec-chatbot-paper/02-diagrams.html): DFD, Sequence, and ER diagrams.
*   [User Stories](https://dijavaji.github.io/tec-chatbot-paper/05-userhistory-document.html): Functional requirements and acceptance criteria.

---

## 📄 License
This project is licensed under the **Apache License 2.0** - see the [LICENSE](LICENSE) file for details.

Developed by **dvasquez** as part of the Technoloqie.

# Dynamic SDUI Banking Platform 🚀

A modern, highly flexible Server-Driven UI (SDUI) Android application powered by AI. This platform allows the app's entire UI and navigation to be generated, modified, and served dynamically from a remote backend using Google Gemini.

## 🏗️ System Architecture

The ecosystem consists of four main pillars:

1. **Android SDUI Client:** A native Android app that parses incoming JSON and paints UI screens in real-time.
2. **PHP/MySQL Backend:** The remote server that stores the dynamic JSON payloads, handles user sessions, and brokers AI requests.
3. **Gemini AI Generator:** Parses natural language intents ("Create a home loan page") and outputs strict SDUI JSON.
4. **Local MCP Server:** A developer tool that allows AI assistants (like Claude/Copilot) to securely manage the remote database via natural language commands.

### 🎥 How the SDUI Flow Works
When a user requests a screen that doesn't exist, the backend asks Gemini to design one on the fly, caches it in MySQL, and delivers it to the Android app. 

*(Check out the `docs/sdui_architecture.gif` or open `docs/sdui_flow.html` in your browser for a fully animated breakdown of this flow!)*

## 🛠️ Technology Stack & Libraries

### Android (Frontend)
*   **Kotlin & Jetpack Compose:** The core language and declarative UI framework used to build the dynamic `RendererFactory`.
*   **Retrofit & OkHttp:** For handling all REST API communications with the PHP backend.
*   **Kotlin Coroutines & Flow:** For asynchronous data handling and state management (`StateFlow`, `SharedFlow` for error handling).
*   **Coil:** For dynamic image loading within the UI widgets.
*   **Material 3:** Providing the core UI components (`AlertDialog`, `Scaffold`, buttons) mapped from the JSON payload.

### Backend (API & Database)
*   **PHP 8:** Core API language for handling `screen.php`, `ai_screen.php`, and the `mcp_bridge.php`.
*   **MySQL:** Stores the `dynamic_pages` (containing the UI JSONs) and `users` tables.

### MCP (Model Context Protocol) Server
*   **Node.js & TypeScript:** The runtime and language for the local MCP server (`mcp-server/index.ts`).
*   **@modelcontextprotocol/sdk:** Standard SDK to expose database tools to AI clients.
*   **dotenv:** For securely managing environment variables.

## 📂 Project Structure

*   `/app` - The Android SDUI client source code.
*   `/backend` - The PHP APIs and SQL schema for the remote server.
*   `/mcp-server` - The Node.js/TypeScript MCP Server for AI integration.
*   `/docs` - Architecture diagrams, animations, and GIFs.

## 🚀 Recent Updates
*   **Robust Error Handling:** The Android app now safely intercepts network/AI errors and displays them via a native Material `AlertDialog` instead of crashing or generating broken UI screens.
*   **Strict AI Filtering:** The PHP backend now strictly blocks irrelevant/test queries (e.g. "test", "hello") from reaching the AI, instantly returning a structured error.
*   **Custom MCP Server:** Added a local MCP Server that proxies requests to the remote database via a secure PHP bridge (`mcp_bridge.php`), allowing developers to query active pages and clear AI caches using natural language.

import { Server } from "@modelcontextprotocol/sdk/server/index.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import { CallToolRequestSchema, ListToolsRequestSchema } from "@modelcontextprotocol/sdk/types.js";
import mysql from "mysql2/promise";
import dotenv from "dotenv";

// Load environment variables from .env
dotenv.config();

// Create the MCP server instance
const server = new Server(
    {
        name: "banking-mcp-server",
        version: "1.0.0",
    },
    {
        capabilities: {
            tools: {},
        },
    }
);

// We will use standard HTTP fetch to communicate with our PHP bridge
const BRIDGE_URL = "https://missiongiveback.in/dynamic_api/api/mcp_bridge.php";
const SECRET_TOKEN = "mcp_secret_12345";

// Helper function to make API calls to the bridge
async function callBridge(action: string, params: string = "") {
    const url = `${BRIDGE_URL}?token=${SECRET_TOKEN}&action=${action}${params}`;
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`Bridge API Error: ${response.statusText}`);
    }
    return response.json();
}

// 1. Tell the AI what tools are available
server.setRequestHandler(ListToolsRequestSchema, async () => {
    return {
        tools: [
            {
                name: "list_dynamic_pages",
                description: "List all dynamic UI pages currently in the banking database.",
                inputSchema: {
                    type: "object",
                    properties: {},
                },
            },
            {
                name: "get_page_json",
                description: "Fetch the exact UI JSON for a specific dynamic page ID.",
                inputSchema: {
                    type: "object",
                    properties: {
                        page_id: {
                            type: "string",
                            description: "The unique ID of the page (e.g. 'home', 'profile', 'ai_xxx')",
                        },
                    },
                    required: ["page_id"],
                },
            },
            {
                name: "clear_ai_cache",
                description: "Delete all AI-generated UI pages (ones starting with 'ai_') from the database.",
                inputSchema: {
                    type: "object",
                    properties: {},
                },
            },
        ],
    };
});

// 2. Handle what happens when the AI actually CALLS a tool
server.setRequestHandler(CallToolRequestSchema, async (request) => {
    const { name, arguments: args } = request.params;
    
    try {
        if (name === "list_dynamic_pages") {
            const rows = await callBridge("list_dynamic_pages");
            return {
                content: [{ type: "text", text: JSON.stringify(rows, null, 2) }],
            };
        } 
        
        else if (name === "get_page_json") {
            const pageId = String(args?.page_id);
            const rows = await callBridge("get_page_json", `&page_id=${encodeURIComponent(pageId)}`);
            if (rows.length === 0) {
                return { content: [{ type: "text", text: `No page found with ID: ${pageId}` }] };
            }
            return {
                content: [{ type: "text", text: rows[0].ui_json }],
            };
        } 
        
        else if (name === "clear_ai_cache") {
            const info = await callBridge("clear_ai_cache");
            return {
                content: [{ type: "text", text: `AI Cache Cleared! Deleted ${info.affectedRows} pages.` }],
            };
        } 
        
        else {
            throw new Error(`Unknown tool: ${name}`);
        }
    } catch (error: any) {
        return {
            content: [{ type: "text", text: `Bridge Error: ${error.message}` }],
            isError: true,
        };
    }
});

// Start the server using stdio (which AI clients like Claude Desktop use)
async function startServer() {
    const transport = new StdioServerTransport();
    await server.connect(transport);
    console.error("Banking MCP Server running on stdio");
}

startServer().catch(console.error);

import mysql from "mysql2/promise";
import dotenv from "dotenv";

dotenv.config();

async function testDb() {
    try {
        console.log("Connecting to DB...");
        const db = await mysql.createConnection({
            host: process.env.DB_HOST,
            user: process.env.DB_USER,
            password: process.env.DB_PASSWORD,
            database: process.env.DB_NAME,
        });
        const [rows] = await db.query("SHOW TABLES;");
        console.log("Tables:", rows);
        db.end();
    } catch (e: any) {
        console.error("DB Connection Failed:", e.message);
    }
}

testDb();

from PIL import Image, ImageDraw, ImageFont
import math

width, height = 1000, 500
frames = []

# Define node positions (centered X, Y)
# HTML coordinates: 
# AI: 50, 50. Size: ~100x60 -> Center: 100, 80
# MCP: 350, 50 -> Center: 400, 80
# Bridge: 530, 200 -> Center: 580, 230
# DB: 800, 200 -> Center: 850, 230
# Android: 50, 350 -> Center: 100, 380
# API: 350, 350 -> Center: 400, 380

nodes = {
    "AI Client": (120, 80, "#c084fc"),
    "MCP Server": (420, 80, "#f97316"),
    "Android App": (120, 380, "#22c55e"),
    "PHP API": (420, 380, "#6366f1"),
    "Gemini AI": (740, 380, "#3b82f6"),
    "PHP Bridge": (600, 230, "#6366f1"),
    "MySQL DB": (850, 230, "#f59e0b"),
}

# Define the paths for the packets (Start Node, End Node, duration multiplier, delay in frames)
paths = [
    ("AI Client", "MCP Server", 0),
    ("MCP Server", "PHP Bridge", 5),
    ("PHP Bridge", "MySQL DB", 10),
    ("Android App", "PHP API", 0),
    ("PHP API", "MySQL DB", 5),
    ("PHP API", "Gemini AI", 15)
]

def draw_rounded_rect(draw, x, y, w, h, r, color):
    draw.rectangle([x+r, y, x+w-r, y+h], fill=color)
    draw.rectangle([x, y+r, x+w, y+h-r], fill=color)
    draw.pieslice([x, y, x+r*2, y+r*2], 180, 270, fill=color)
    draw.pieslice([x+w-r*2, y, x+w, y+r*2], 270, 360, fill=color)
    draw.pieslice([x, y+h-r*2, x+r*2, y+h], 90, 180, fill=color)
    draw.pieslice([x+w-r*2, y+h-r*2, x+w, y+h], 0, 90, fill=color)

try:
    font = ImageFont.truetype("Arial", 16)
    title_font = ImageFont.truetype("Arial", 24)
except:
    font = ImageFont.load_default()
    title_font = ImageFont.load_default()

total_frames = 40  # Total frames for a full loop (forward and back)

for i in range(total_frames):
    img = Image.new("RGB", (width, height), "#1a1a2e")
    draw = ImageDraw.Draw(img)
    
    draw.text((350, 20), "Dynamic UI Architecture (Live Flow)", fill="#ffffff", font=title_font)
    
    # Draw zones
    # Local: 20, 10, 450x120
    draw.rectangle([20, 30, 470, 440], outline="#4a4e69", width=2)
    draw.text((30, 40), "Local & App", fill="#4a4e69", font=font)
    
    # Remote: 300, 160, 620x280
    draw.rectangle([500, 100, 980, 440], outline="#4a4e69", width=2)
    draw.text((510, 110), "Remote Server (missiongiveback.in)", fill="#4a4e69", font=font)
    
    # Draw connections
    draw.line([nodes["AI Client"][:2], nodes["MCP Server"][:2]], fill="#4a4e69", width=4)
    draw.line([nodes["MCP Server"][:2], nodes["PHP Bridge"][:2]], fill="#4a4e69", width=4)
    draw.line([nodes["PHP Bridge"][:2], nodes["MySQL DB"][:2]], fill="#4a4e69", width=4)
    draw.line([nodes["Android App"][:2], nodes["PHP API"][:2]], fill="#4a4e69", width=4)
    draw.line([nodes["PHP API"][:2], nodes["MySQL DB"][:2]], fill="#4a4e69", width=4)
    draw.line([nodes["PHP API"][:2], nodes["Gemini AI"][:2]], fill="#4a4e69", width=4)
    
    # Draw nodes
    for name, pos in nodes.items():
        cx, cy, color = pos
        draw_rounded_rect(draw, cx-60, cy-30, 120, 60, 10, color)
        draw.text((cx-40, cy-10), name, fill="white", font=font)
    
    # Draw packets
    # A full cycle is 0 to 1 (0 -> 0.5 is forward, 0.5 -> 1.0 is backward)
    for start_name, end_name, delay in paths:
        start_node = nodes[start_name]
        end_node = nodes[end_name]
        
        # Calculate current progress based on frame and delay
        frame_progress = (i - delay) % total_frames
        progress = frame_progress / float(total_frames)
        
        if progress <= 0.5:
            # Forward
            p = progress * 2
            px = start_node[0] + (end_node[0] - start_node[0]) * p
            py = start_node[1] + (end_node[1] - start_node[1]) * p
            color = "#ffffff"
        else:
            # Backward
            p = (progress - 0.5) * 2
            px = end_node[0] + (start_node[0] - end_node[0]) * p
            py = end_node[1] + (start_node[1] - end_node[1]) * p
            color = "#fde047"
            
        draw.ellipse([px-6, py-6, px+6, py+6], fill=color)
    
    frames.append(img)

frames[0].save('mcp_architecture.gif', save_all=True, append_images=frames[1:], optimize=False, duration=100, loop=0)
print("GIF created successfully!")

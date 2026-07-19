from PIL import Image, ImageDraw, ImageFont
import math

width, height = 800, 400
frames = []

# Define node positions
nodes = {
    "Android": (100, 200, "#22c55e"),
    "PHP API": (350, 200, "#6366f1"),
    "Gemini AI": (600, 200, "#c084fc"),
    "MySQL DB": (350, 320, "#f59e0b"),
}

# Animation steps: 
# 1. Android -> PHP
# 2. PHP -> DB (fetch context)
# 3. DB -> PHP
# 4. PHP -> Gemini
# 5. Gemini -> PHP (JSON)
# 6. PHP -> DB (insert)
# 7. PHP -> Android (render)

steps = [
    ("Android", "PHP API", "1. Query"),
    ("PHP API", "MySQL DB", "2. Get Context"),
    ("MySQL DB", "PHP API", ""),
    ("PHP API", "Gemini AI", "3. Ask AI"),
    ("Gemini AI", "PHP API", "4. Get JSON"),
    ("PHP API", "MySQL DB", "5. Cache JSON"),
    ("MySQL DB", "PHP API", ""),
    ("PHP API", "Android", "6. Render UI"),
]

frames_per_step = 10

def draw_rounded_rect(draw, x, y, w, h, r, color):
    draw.rectangle([x+r, y, x+w-r, y+h], fill=color)
    draw.rectangle([x, y+r, x+w, y+h-r], fill=color)
    draw.pieslice([x, y, x+r*2, y+r*2], 180, 270, fill=color)
    draw.pieslice([x+w-r*2, y, x+w, y+r*2], 270, 360, fill=color)
    draw.pieslice([x, y+h-r*2, x+r*2, y+h], 90, 180, fill=color)
    draw.pieslice([x+w-r*2, y+h-r*2, x+w, y+h], 0, 90, fill=color)

# Try to load a font, otherwise use default
try:
    font = ImageFont.truetype("Arial", 16)
    title_font = ImageFont.truetype("Arial", 24)
except:
    font = ImageFont.load_default()
    title_font = ImageFont.load_default()

for step in steps:
    start_node = nodes[step[0]]
    end_node = nodes[step[1]]
    label = step[2]
    
    for i in range(frames_per_step):
        img = Image.new("RGB", (width, height), "#0f172a")
        draw = ImageDraw.Draw(img)
        
        draw.text((250, 40), "SDUI Architecture Flow", fill="#38bdf8", font=title_font)
        
        # Draw connections
        draw.line([(100, 200), (350, 200)], fill="#334155", width=4)
        draw.line([(350, 200), (600, 200)], fill="#334155", width=4)
        draw.line([(350, 200), (350, 320)], fill="#334155", width=4)
        
        # Draw nodes
        for name, pos in nodes.items():
            cx, cy, color = pos
            draw_rounded_rect(draw, cx-60, cy-30, 120, 60, 10, color)
            # Center text manually
            draw.text((cx-40, cy-10), name, fill="white", font=font)
        
        # Draw packet
        progress = i / float(frames_per_step)
        px = start_node[0] + (end_node[0] - start_node[0]) * progress
        py = start_node[1] + (end_node[1] - start_node[1]) * progress
        
        draw.ellipse([px-10, py-10, px+10, py+10], fill="#fde047")
        if label:
            draw.text((px-20, py-30), label, fill="#fde047", font=font)
        
        frames.append(img)

# Hold last frame
for _ in range(10):
    frames.append(frames[-1])

frames[0].save('sdui_architecture.gif', save_all=True, append_images=frames[1:], optimize=False, duration=100, loop=0)
print("GIF created successfully!")

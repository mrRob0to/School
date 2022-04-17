# Robert Antenorcruz
# Phys204 - Project 2
# Random Walk Simulation
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation

# Initializing  Graphs 1D and 2D
fig, (ax1, ax2) = plt.subplots(2, figsize=(12, 8))
plt.xlabel('Steps')
plt.ylabel('Value')
ax1.grid(True, which='major', linestyle='--', color='black', alpha=0.4)
ax1.set_title("1D Random Walk")
ax2.set_title("2D Random Walk")
ax1.locator_params(nbins=20, axis='x')
ax2.set_xlim([-10, 10])
ax2.set_ylim([-10, 10])

# Parameters 1D
n = 1000
step_direction = [-1, 0, 1]
step_dimensions = (n, 1)  # 1 for 1D, 2 for 2D

# Run1
steps = np.random.choice(step_direction, step_dimensions)
path = steps.cumsum(0)
ax1.plot(path, ls='none')
line, = ax1.plot([], [], lw=1.5, c="blue", ls="solid")
xdata, ydata = [], []

# Run2
steps2 = np.random.choice(step_direction, step_dimensions)
path2 = steps2.cumsum(0)
ax1.plot(path2, ls='none')
line2, = ax1.plot([], [], lw=1.5, c="green", ls="solid")
xdata2, ydata2 = [], []

# Run3
steps3 = np.random.choice(step_direction, step_dimensions)
path3 = steps3.cumsum(0)
ax1.plot(path3, ls='none')
line3, = ax1.plot([], [], lw=1.5, c="red", ls="solid")
xdata3, ydata3 = [], []

# Run4
steps4 = np.random.choice(step_direction, step_dimensions)
path4 = steps4.cumsum(0)
ax1.plot(path4, ls='none')
line4, = ax1.plot([], [], lw=1.5, c="orange", ls="solid")
xdata4, ydata4 = [], []

# Parameters 2D
n2d = 1000
step_direction2d = [-1, 0, 1]
step_dimensions2d = (n2d, 2)
steps2d = np.random.choice(step_direction2d, step_dimensions2d)
path2d = steps2d.cumsum(0)
xdata2d, ydata2d = [], []
line2d, = ax2.plot([], [], lw=1, c="magenta", ls="dashed")


def find2dPos():
    for x in range(len(path2d)):
        pos = path2d[x]
        yield pos[0], pos[1]


def init():
    line2d.set_data(xdata2d, ydata2d)
    return line2d,


# Animiation functions
def animate(i):
    y = path[i]
    x = i
    xdata.append(x)
    ydata.append(y)
    line.set_data(xdata, ydata)

    y2 = path2[i]
    x2 = i
    xdata2.append(x2)
    ydata2.append(y2)
    line2.set_data(xdata2, ydata2)

    y3 = path3[i]
    x3 = i
    xdata3.append(x3)
    ydata3.append(y3)
    line3.set_data(xdata3, ydata3)

    y4 = path4[i]
    x4 = i
    xdata4.append(x4)
    ydata4.append(y4)
    line4.set_data(xdata4, ydata4)
    return line, line2, line3, line4


def animate2(i):
    x, y = i
    print(path2d)
    xdata2d.append(x)
    ydata2d.append(y)
    line2d.set_data(xdata2d, ydata2d)

    return line2d,


anim = animation.FuncAnimation(fig, animate, frames=n, interval=10, blit=False, repeat=False)
anim2 = animation.FuncAnimation(fig, animate2, find2dPos, interval=100, blit=False, repeat=False)

plt.show()

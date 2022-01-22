from PIL import Image
import random

def glass_filter(pixel_map, width, height):
    for i in range(width):
        for j in range(height):

            rgbp = lambda x,y: input_image.getpixel((x, y))

            possible_values = [rgbp(i, j)]

            # add pixel above if possible
            if j != 0:
                possible_values.append(rgbp(i, j - 1))
            # add pixel below if possible
            if j != height - 1:
                possible_values.append(rgbp(i, j + 1))
            # pixel left
            if i != 0:
                possible_values.append(rgbp(i - 1, j))
            # pixel right
            if i != width - 1:
                possible_values.append(rgbp(i + 1, j))
            
            # choose random rgb val
            rand_index = random.randrange(0, len(possible_values))

            # set pixel value
            pixel_map[i, j] = possible_values[rand_index]
            
if __name__ == "__main__":
    fpath = input("enter path for image relative to ~/Pictures: ")
    BASE_DIR = "/home/harishb/Pictures"
    input_image = Image.open(f"{BASE_DIR}/{fpath}")
    before_ext = fpath[0: fpath.rfind(".")]
    after_ext = fpath[fpath.rfind(".") + 1: ]

    pixel_map = input_image.load()

    width, height = input_image.size

    glass_filter(pixel_map, width, height)

    # save as final output
    new_ext = "png"
    new_fpath = f"{BASE_DIR}/{before_ext}_glass.{new_ext}"
    input_image.save(new_fpath, format=new_ext)

    print(f"Picture succesfully saved at {new_fpath}")
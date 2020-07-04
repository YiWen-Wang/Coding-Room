import sys
scope = {}
def runCode(codeStr):
    print("run codes ")
    r = redirect()
    sys.stdout = r
    result = {}
    try:
        exec(codeStr,scope)
        result["status"] = True
        result["entityValue"] = r.content
    except Exception as e:
        result["status"] = False
        result["reason"] = str(e)
    return result

class redirect:
    content = ""
    def write(self,str):
        self.content += str
    def flush(self):
        self.content = ""







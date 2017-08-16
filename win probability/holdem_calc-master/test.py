import holdem_calc

def test():
	playerDict = {}
	playerDict[1] = ["Qs", "8c"]
	playerDict[2] = ["3h", "2h"]
	playerDict[3] = ["8d", "8h"]

	results = holdem_calc.calculate(["5d", "Ad", "Qh", "6d"],  # board as list of strings
									False, # use exact
									10000, # Number of Simulations
									playerDict)  # players cards

	resultDict = {}
	for idx, result in enumerate(results):
		resultDict[idx + 1] = round(result * 100)

	print(resultDict)

test()
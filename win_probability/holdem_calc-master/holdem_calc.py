import holdem_functions

def run(playerDict, exact, num, board):
    playerDict = holdem_functions.parseCards(playerDict)
    board = holdem_functions.parseBoard(board)
    deck = holdem_functions.generate_deck(playerDict, board)

    # Create results data structures which track results of comparisons
    # 1) result_histograms: a list for each player that shows the number of
    #    times each type of poker hand (e.g. flush, straight) was gotten
    # 2) winner_list: number of times each player wins the given round
    # 3) result_list: list of the best possible poker hand for each pair of
    #    hole cards for a given board
    num_players = len(playerDict)
    result_histograms = []
    winner_list = [0] * num_players
    for _ in range(num_players):
        result_histograms.append([0] * len(holdem_functions.hand_rankings))

    # Choose whether we're running a Monte Carlo or exhaustive simulation
    # When a board is given, exact calculation is much faster than Monte Carlo
    # simulation, so default to exact if a board is given
    if exact or board is not None:
        generate_boards = holdem_functions.generate_exhaustive_boards
    else:
        generate_boards = holdem_functions.generate_random_boards



    holdem_functions.find_winner(generate_boards,
                                 deck,
                                 playerDict,
                                 num,
                                 board,
                                 winner_list,
                                 result_histograms)

    for _ in range(num_players):
        print(result_histograms[len(result_histograms)-1])
    return holdem_functions.find_winning_percentage(winner_list)

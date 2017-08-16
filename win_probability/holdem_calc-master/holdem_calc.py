import holdem_functions


def calculate(board, exact, num, playerDict):
    playerDict = holdem_functions.parseCards(playerDict)
    board = holdem_functions.parseBoard(board)
    return run(playerDict, exact, num, board)

def run(playerDict, exact, num, board):
    deck = holdem_functions.generate_deck(playerDict, board)
    return run_simulation(playerDict, exact, num, board, deck)

def run_simulation(playerDict, exact, num, given_board, deck):
    num_players = len(playerDict)
    # Create results data structures which track results of comparisons
    # 1) result_histograms: a list for each player that shows the number of
    #    times each type of poker hand (e.g. flush, straight) was gotten
    # 2) winner_list: number of times each player wins the given round
    # 3) result_list: list of the best possible poker hand for each pair of
    #    hole cards for a given board
    result_histograms = []
    winner_list = [0] * num_players
    for _ in range(num_players):
        result_histograms.append([0] * len(holdem_functions.hand_rankings))
    # Choose whether we're running a Monte Carlo or exhaustive simulation
    board_length = 0 if given_board is None else len(given_board)
    # When a board is given, exact calculation is much faster than Monte Carlo
    # simulation, so default to exact if a board is given
    if exact or given_board is not None:
        generate_boards = holdem_functions.generate_exhaustive_boards
    else:
        generate_boards = holdem_functions.generate_random_boards

    holdem_functions.find_winner(generate_boards, deck, playerDict, num,
                                 board_length, given_board, winner_list,
                                 result_histograms)
    return holdem_functions.find_winning_percentage(winner_list)

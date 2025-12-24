<script>
  let {
    open = false,
    onClose,
    onSubmit
  } = $props();

  let symbol = $state("");
  let price = $state(0);

  function submit(e) {
    e.preventDefault();

    onSubmit({
      symbol,
      price
    });

    symbol = "";
    price = 0;
    onClose();
  }
</script>

{#if open}
  <div class="modal-overlay">
    <div class="modal">
      <button class="close-btn" type="button" onclick={onClose}>×</button>

      <div class="modal-header">
        <h2 class="modal-title">Update Price</h2>
        <p class="modal-subtitle">Modify Investment Value</p>
      </div>

      <form onsubmit={submit}>
        <div class="form-group">
          <label class="form-label" for="update-symbol">Symbol</label>
          <input
            id="update-symbol"
            class="form-input"
            bind:value={symbol}
            required
          />
        </div>

        <div class="form-group">
          <label class="form-label" for="update-price">New Price</label>
          <input
            id="update-price"
            type="number"
            step="0.01"
            class="form-input price-input"
            bind:value={price}
            required
          />
        </div>

        <div class="button-group">
          <button type="button" class="btn btn-secondary" onclick={onClose}>
            Cancel
          </button>
          <button type="submit" class="btn btn-primary">
            Apply Update
          </button>
        </div>
      </form>
    </div>
  </div>
{/if}
